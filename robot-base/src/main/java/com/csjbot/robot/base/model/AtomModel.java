package com.csjbot.robot.base.model;

import java.util.Date;

public abstract class AtomModel extends BaseModel {
    private static final long serialVersionUID = -5660132899036106237L;
    protected Date dateOfCreate;
    protected Date dateOfUpdate;
    protected String creator_fk;
    protected String updater_fk;

    public AtomModel() {
        this.dateOfCreate = new Date();

        this.dateOfUpdate = new Date();
    }

    public Date getDateOfCreate() {
        return this.dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Date getDateOfUpdate() {
        return this.dateOfUpdate;
    }

    public void setDateOfUpdate(Date dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public String getCreator_fk() {
        return this.creator_fk;
    }

    public void setCreator_fk(String creator_fk) {
        this.creator_fk = creator_fk;
    }

    public String getUpdater_fk() {
        return this.updater_fk;
    }

    public void setUpdater_fk(String updater_fk) {
        this.updater_fk = updater_fk;
    }
}