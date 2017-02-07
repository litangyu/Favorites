package com.acg233.favorites.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JokAr on 16/7/5.
 */
public class DownloadResponse implements Parcelable {
    private int progress;
    private long currentFileSize;
    private long totalFileSize;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(long currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.progress);
        dest.writeLong(this.currentFileSize);
        dest.writeLong(this.totalFileSize);
    }

    public DownloadResponse() {
    }

    protected DownloadResponse(Parcel in) {
        this.progress = in.readInt();
        this.currentFileSize = in.readLong();
        this.totalFileSize = in.readLong();
    }

    public static final Parcelable.Creator<DownloadResponse> CREATOR = new Parcelable
            .Creator<DownloadResponse>() {
        @Override
        public DownloadResponse createFromParcel(Parcel source) {
            return new DownloadResponse(source);
        }

        @Override
        public DownloadResponse[] newArray(int size) {
            return new DownloadResponse[size];
        }
    };
}