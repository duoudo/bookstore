package com.bookStore.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800619577";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA6WyCqQlDUx08x1HZBkZoAmr0/4bVKIl2xh5/CXh+9lcbTSbp4FNcD6IWQ2JHSlDtjuvlKqjmRghEoqHWjsCBAjUlNOhdZNDYYU/rnTsb0wyLBk6Y+44RseskJJFI8lawtK33gPm+u4NCNBdRTLlQb3JGb+H7DntV8RpPCVQcHQcnsmmrbHHyY99F0Hf88sWppS3nuXknzdF7QsL322otachOH3XvDGh9I9qftMtEBedqWzcIQaU4kSLCEmSm+tdWBOnM3BmKW+URrVQoKPHRuacqVrq3o+OMTCGpg1O9uFY30PHYAHppjXWNBmVDO0Qi9uxzRFRHh5bADpP//U3xAgMBAAECggEADRUjYQM8oGt/7RohUVAUtxt/tUt1aI6LnNUwZR4V73SalHe4+kU80Hv5rB68i5WcLu+xOvmgWHZFAooquHaWTMFnmVYMOuYvV7ZQgQ3c4M8DV9yU/2uQGHWg8nMPl4KYj6hWe2bIcbXf9UkKEMMjj2kwBLv4CB2JIMiuLdU5hvy6ScbpKQClKAIzIrwBBAlFOeD2MiRwTijOIgaIqEKTni5Csuy7uk4SlXJ/tnfRbkWaX/+brmU5iFJ6O63AP5aAQJXePzD0pPhdKGsgf4myKWjwbADQRkZOi3SwW3psZ3c+qjEJT8XHRAxAg4z/2xKBq8WxxS1A9CEGLDr6GKTYjQKBgQC6TzO3mopkVHxVotiks4iXkFgi9gs3eMYVsrNbrmG1HxUePp45qK6+LCZkgKrXddauBM8thX4J2dTxmnSGtRzsT/1OyF7JMDH0ELzBYBDtDphd9sxnI4mCCADssgchn8B7HgOe/zvLn+cJZuurFlDlx0i1DZPx7t2sHNzbalkhvwKBgQCxIeN3S1uF39MrrxZRw82q9MQq9tBhYHeJe6Ft4Hbm8u4hX7PJavpdPQyBSOVj7LTC3IWLanrwkR5l9SgDEzWRJSAXiChVUgDn2fAdXDKWrOrKOTpVJzrL/ZF1jBrvVyPYbZt1iLRmWvhY1ZAHztS1VfhKJaPUzV4eAy8DnfUcTwKBgB5pk12jQngCOpGYFZk9HUzwaRK76zTQ6DTeiNysaJKyUjYvrpBm8q9kcZfyUPD+8V5StqcNpXdiMZqGxgK/Scthe04C80YQMfdPTCCc1+Z6fXDJOrCTYnzxZYU9wmBAvaDbU2f+7fndTk+Al3PEHsyDbWDwqHfTsCfKRlsizw3tAoGBALEUvgCO96p4vvYXDeskIsJb/JkvMdeHxzlMvW/JTVVJW8NFl8kvB6ia1ZuSMrGY/IUqTN+5zboeehNX6W5Zj93RDao41omld35SJHhVqcV0jS0p1SU4GNHCZ97p+Ct6JD3EJfx0dFU3cLvulRUZMxEuJxCJXKXrwcGjUSb+jQKFAoGANLmg3rnwOCDLZWh0zHR9OkGKMCryTBkNuGVmEFsRGrFGQe6xVoqiXNG7NYqnMcMx70roiMmPDCpv5MwdQVU0mdxsQRQCUE1HMbJVL+ZwnaRdYCHuqMnkDN7dMmfxi1BKwKxsPt0tSAwX8XhJCOYXBdlzV3kNyzbPdV3KnGPuVl0=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl04V1xjh5ISX9k73fzhHcaSvPPJ2mJhU7JOhDrIOMXbZ4gW885RZJ9klfO9vIEugi4LnBqs0fLbNwfOEQ4gDC824u5KGQFbp2fjVhs/IFtmVYToU4Mbcj6F6k4F66zRSGRbKl67CzMu4SmUN+pzOv+lmJ8aoIJB5m5zOtEd2zUK5XbcTJId+QyktPKh83EpkUyrSQDMHj9hsvem0QXWbGQjwPtgY5TgOX1ZfpNQroHhucw4J1ypyobf0FaxtjuAHZoRbS3lQXAL8dXTNGX/2hFqAH114WIHxGwJ70TNPF+W08UUH7wDe6RxYIHvxjJp88v/jV5WImcw1mOSJABfPVQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/bookstore/client/return_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/bookstore//client/cart/paysuccess.do";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

