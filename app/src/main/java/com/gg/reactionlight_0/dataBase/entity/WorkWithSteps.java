package com.gg.reactionlight_0.dataBase.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class WorkWithSteps {

    @Embedded
    public Work work;

    @Relation(parentColumn = "id", entityColumn = "owner", entity = Step.class)
    public List<Step> steps;


}
