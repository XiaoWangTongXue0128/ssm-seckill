package com.duyi.seckill.dto;

public class SeckillUrl {


    // true：seckillId有对应的商品
    // false：seckillId没有对应的商品
    private boolean enable;
    private String md5;
    private int seckillId;
    private long now;
    private long start;
    private long end;

    public SeckillUrl(boolean enable, int seckillId, long now, long start, long end) {
        this.enable = enable;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public SeckillUrl(boolean enable, String md5, int seckillId, long now, long start, long end) {
        this.enable = enable;
        this.md5 = md5;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public SeckillUrl(boolean enable, int seckillId) {
        this.enable = enable;
        this.seckillId = seckillId;
    }

    public SeckillUrl() {
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
