package com.nikesh.displaypostsexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostsModel {

    @Expose
    @SerializedName("hits")
    private List<hitsList> hits;

    public List<hitsList> getData() {
        return hits;
    }

    public boolean apiInProgress = false;

    public boolean isApiInProgress() {
        return apiInProgress;
    }

    public void setApiInProgress(boolean apiInProgress) {
        this.apiInProgress = apiInProgress;
    }

    public void setData(List<hitsList> data) {
        this.hits = data;
    }

    public class hitsList {
        @Expose
        @SerializedName("created_at")
        private String created_at;

        private boolean count;

        public boolean isCount() {
            return count;
        }

        public void setCount(boolean count) {
            this.count = count;
        }

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("objectID")
        private String objectID;

        @Expose
        @SerializedName("disabled")
        private boolean disabled = true;

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
