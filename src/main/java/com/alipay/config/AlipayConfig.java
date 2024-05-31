package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author gdx
 * @description
 * @date 2020/1/13
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

        // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
        public static String app_id = "9021000137624282";

        // 商户私钥，您的PKCS8格式RSA2私钥
        public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCsl3+snqpoCFQhc2540q4PIAPxq8XzIpXgu8lQGjRlafRyESLVzRStjUa5bvRFarfkYjfdiyL1jb4w6ZbgmwPAay365Ubd2rxDcfbLWbZAJhnHs9sELcvCuuUWug84Nmq6uS55BbS02d18xFE0u8Zop1jY6dywLmGrOXkMNTe18Wcmv8klYYudyB0D2P+drx8xZLGAxFtk2aBunlE50sbWGdra9Hf7VpNsrCpMumIh+Zw0Ak8s9dFXm45c88EiddMHUKqhmxK3su+7C0D1bmHLGvu49TD8BnFWTMS6osG3buHFrv9UgiXTIe+E4qcY4j0hkJe8xen57Cs2j5ppb5jVAgMBAAECggEABXY96h6TUfuBfhWDF3vDWmMu/MTs3Mbpa1VGB6zsn5D81WdJbO6yitN/u3qRbGj2LNoW3oH6tz6nGDEsPytosPwzVk9PMNItRw0iPEk4hUsvYayFb9prKlxzx4ZX2PWP5xzZJadSkq6aXcpwr3SJ0OIK48Jy43h0StDkzcgZXT6a9ozpa+a7Olrg237BbJ/taNAHAdHUkx4nEQzBGKuvs6sIYTDjsaZXy4MIk3IyydfwOBb0j79io1GVxDoAJ8kHnIzTd3ozwkxTvWCJT1rBK/W5/h4KmR4AeZsgg6nWvYXAUJJcCY4gVn//tUmU1FKcbVYLus3g/K4r3TMU1Bh5OQKBgQD8/0L7hGd1+FyXIJNP2MXDPpQSr0HAz8g+R6ybl/c0hjhTfGFrJwRVfoFZQ6RHK5Q9pM51vY/KQrtFdtgORX+oHKNnFb1KaE3uzRxoKcqBxAuu6jdZ8fh0JjT061NkcG56D3PVIwdjhG0tdRCNVlK7YYxswrOb8maRCweLonShJwKBgQCuo+xj3qytFni0QKKkPjPokhzsmZ12q3FkObpkuzAzWSvUXS71zeXvsRbPP4xTPiTt/CwduvIzLwQxeEgWYGUVvPPdjaKg/ycJHM2umEi3dBBLGUp3HL4OBe2VNHtBtQsKW00cYRq9yVtd7Bzi97Sw6HGlEhDZcIs2g4vB2087owKBgC7cbSC/TpaYUTYg74hLo53dennXgntfQTzf88XCKSTOc6JrGHRLfs8DJpxNHiGcndGpNCC04uxY3e0o7S3/i3ytCUsvyKb+mOjRjyBwFTViBbP5aBU974I8DXUMHZ8uABiVwiPJYDX7smlIFK/JPHu+vs7qZXBOPtdAzr37clTjAoGAVSAwFMowrzoZ4frzjE2Jrd6y3J1QRMzyu6Dr+zfHGfBoTd6SClyvrPNMI/9c3iKXi610r5U1g79yMs7njIi2HS96S09Dq2hT1bxFgcpW/51CZ+jPBFptRZacJTNstS2TURwJ7UBj8YiZ0aFKv1Nwrx7ObmDs96BFK3BZikflSjMCgYAnubXQERbIV7jQlvUU/Zc7CDbBOuuek/iQq6zQqZyDEI0SS7Yn4aPodVfOBfaltYkRGdj5bFIN4namz5XzADCCcAtwG8wRblKM6z/HJlLrq3b5uvaUGsQemj2StXUAiD4Z6iLmkznKx1osOvU5vyaXdSpWSePqRbJwPB9PA2Madg==";
        // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
        public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj532iEBNHwC9wMZFsoJqUaJTbfVHxFrCt+KyT8WR65+rml1z8+g0lBuwaEBXmHhDQ8HJGen0bJK5wa6iNESRgnOmpl8pECvEfcaX2DVQ5jNxEPdemfK4biExTTDJiC9z5NEXg1HtGpN5aCL9l2gSV72UmV/I5JbUc1HYCvAkiCcYDQuIYfZGLx1t/KlWVguvc1LzKIbnDNPuwO43UwCynJoS92YAn3F+mPx9W35mwbIq6OBmMQ+LmYNGJONGpt9YOBZf7AvZwe7ksjGY94SlXk56B8ohJA2NROguF2HbQnQ29I6iEexiDP9Amv6ajjHeMhqts+Dyy3J8qWBR6ZjRXQIDAQAB";
        // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

        // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        public static String return_url = "http://localhost:8080/esms/payReturn";

        // 签名方式
        public static String sign_type = "RSA2";

        // 字符编码格式
        public static String charset = "utf-8";

        // 支付宝网关
        public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

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

