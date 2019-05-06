package com.kwb.saller.service;

import com.kwb.entity.VerificationOrder;
import com.kwb.saller.enums.ChanEnum;
import com.kwb.saller.slaverepository.VerificationOrderRepository;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VerificationOrderService {
    @Autowired
    private VerificationOrderRepository verificationOrderRepository;

    private static final Logger logger = LoggerFactory.getLogger(VerificationOrderService.class);

    @Value("${verification.verificationlocation}")
    private String rootDir;

    private static DateFormat DATE_FORMATE = new SimpleDateFormat("yyyy-mm-dd");

    private static String END_LINE = System.getProperty("line.separator", "\n");

    /**
     * 生成某个渠道某天的对账文件
     *
     * @return
     */
    public File mkVerificationFile(String chanId, Date day) {
        File path = getPath(chanId, day, rootDir);
        if (path.exists()) {
        }
        try {
            path.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 构造起始终止时间
         */
        Date start = getStartDate(day);
        Date end = getStopDate(start);
        List<String> orders = verificationOrderRepository.queryVerificationOrders(chanId, start, end);
        String content = String.join(END_LINE, orders);
        FileUtil.writeAsString(path, content);
        return path;
    }

    private Date getStopDate(Date start) {
        return new Date(start.getTime() + 24 * 1000 * 60 * 60);
    }

    private Date getStartDate(Date day) {
        String start_str = DATE_FORMATE.format(day);
        Date start = null;
        try {
            start = DATE_FORMATE.parse(start_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    public File getPath(String chanId, Date day, String rootDir) {
        String name = DATE_FORMATE.format(day) + "-" + chanId + ".txt";
        System.err.println(rootDir);
        File path = Paths.get(rootDir, name).toFile();
        return path;
    }

    /**
     * 解析对账文件
     *
     * @param line
     * @return
     */
    public static VerificationOrder parseLine(String line) {
        VerificationOrder order = new VerificationOrder();
        String[] data = line.split("\\|");
        order.setOrderId(data[0]);
        order.setOuterOrderId(data[1]);
        order.setChanId(data[2]);
        order.setChanUserId(data[3]);
        order.setProductId(data[4]);
        order.setOrderType(data[5]);
        order.setAmount(new BigDecimal(data[6]));

        try {
            order.setCreateAt(DATE_FORMATE.parse(data[7]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    /**
     * 保存渠道订单数据
     *
     * @param chanId
     * @param day
     */
    public void saveChanOrders(String chanId, Date day) {
        //根据渠道名称，FTP地址，ip
        ChanEnum chanEnum = ChanEnum.getByChanId(chanId);

        //获取到队长文件
        //根据配置从ftp下载对账的对账数据
        File file = getPath(chanId, day, chanEnum.getRootDir());
        if (!file.exists()) {
            logger.error("对账文件不存在");
            return;
        }
        String content = null;
        try {
            content = FileUtil.readAsString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = content.split(END_LINE);
        List<VerificationOrder> orders = new ArrayList<>();

        for (String line : lines) {
            orders.add(parseLine(line));
        }

        verificationOrderRepository.save(orders);
    }


    public List<String> verifyOrder(String chanId,Date day) {
        List<String> errors = new ArrayList<>();
        Date start = getStartDate(day);
        Date stop = getStopDate(start);
        List<String> excessOrders = verificationOrderRepository.queryExecessOrders(chanId, start, stop);
        List<String> missOrders = verificationOrderRepository.queryMissOrders(chanId, start, stop);
        List<String> differentOrders = verificationOrderRepository.queryDifferentOrders(chanId, start, stop);
        errors.add("长宽订单号:" + String.join(",", excessOrders));
        errors.add("漏单订单号:" + String.join(",", missOrders));
        errors.add("不一致订单号:" + String.join(",", differentOrders));
        /**
         * 实际应该再添加一张表，将数据保存
         */
        return errors;
    }
    static {

        /**
         * 长款sql
         */
        String changkuan = "select t.order_id from t_order t left join verification_order v on t.chan_id= 'kwb' and " +
                "t.outer_order_id = v.order_id where v.order_id is null and create>_at=  and create_at<=";
        /**
         * 漏单sql
         */
        String loudan = "select v.order_id from verification_order v left join t_order t on v.outer_order_id = t.out_id " +
                "and v.chan_id='kwb' where t.order_id is null";

        /**
         * 不一致数据
         */
        String buyizhi = "select t.order from t_order t left join verfication_order v on t.chan_id = 'kwb' " +
                "and t.outer_order_id = v.order_id and concat_ws('|',t.order_id,t.outer_order_id,t.chan_id,t.chan_user_id" +
                ",t.product_id,t.order_type,t.amount,t.create_at) != concat_ws('|',v.order_id,v.outer_order_id,v.chan_id," +
                "v.chan_user_id,v.product_id,v.order_type,v.amount,v.create_at)" ;
    }
}
