package cn.muses.trade.core.annotation;


import cn.muses.trade.core.filter.HFilter;

public class HawkFilterValue implements Comparable<HawkFilterValue>{
	// 顺序
    private int order;
    private int [] cmds;
    private int [] ignoreCmds;
    private HFilter hfilter;

    public HawkFilterValue(int order, int[] cmds, int[] ignoreCmds, HFilter hfilter) {
        this.order = order;
        this.cmds = cmds;
        this.ignoreCmds = ignoreCmds;
        this.hfilter = hfilter;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

   
    public int[] getCmds() {
        return cmds;
    }

    public void setCmds(int[] cmds) {
        this.cmds = cmds;
    }

    public int[] getIgnoreCmds() {
        return ignoreCmds;
    }

    public void setIgnoreCmds(int[] ignoreCmds) {
        this.ignoreCmds = ignoreCmds;
    }

    public HFilter getHfilter() {
		return hfilter;
	}

	public void setHfilter(HFilter hfilter) {
		this.hfilter = hfilter;
	}

	@Override
    public int compareTo(HawkFilterValue another) {
        return Integer.compare(this.order, another.order);
    }
}
