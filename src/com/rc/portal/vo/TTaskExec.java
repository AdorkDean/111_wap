package com.rc.portal.vo;

public class TTaskExec {
    private String taskid;

    private String lastexecuted;

    private String taskname;

    private String remark;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getLastexecuted() {
        return lastexecuted;
    }

    public void setLastexecuted(String lastexecuted) {
        this.lastexecuted = lastexecuted;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}