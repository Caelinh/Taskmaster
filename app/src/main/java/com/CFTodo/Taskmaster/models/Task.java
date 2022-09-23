package com.CFTodo.Taskmaster.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    public Long id;
    private String title;
    private String description;
    //TODO-1: change state to enum
    private TaskStateEnum state;
    private java.util.Date dateCreated;

    public Task(String title, String description, TaskStateEnum state, Date dateCreated) {
        this.title = title;
        this.description = description;
        this.state = state;
        this.dateCreated = dateCreated;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStateEnum getState() {
        return state;
    }

    public void setState(TaskStateEnum state) {
        this.state = state;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public enum TaskStateEnum {
        Assigned("Assigned"),
        In_Progress("In Progress"),
        Complete("Complete");

        private final String TaskState;

        TaskStateEnum(String _TaskState){
            this.TaskState = _TaskState;
        }

        public String getTaskState() {
            return TaskState;
        }

        public static TaskStateEnum fromString(String possibleTaskType){
            for(TaskStateEnum type: TaskStateEnum.values()){
                if(type.TaskState.equals(possibleTaskType)){
                    return type;
                }
            }
            return null;
        }
        @NonNull
        @Override
        public String toString() {
            if(TaskState == null){
                return "";
            }
            return TaskState;
        }
    }



}
