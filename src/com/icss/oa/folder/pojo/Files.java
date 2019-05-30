package com.icss.oa.folder.pojo;

import java.util.Arrays;
import java.sql.Date;

public class Files {
    private Integer fileId;

    private String fileName;

    private Integer fileSize;

    private Folder fileSuperFolder;

    private String fileState;

    private Date fileCreateDate;

    private Date fileUpdateDate;

    private byte[] fileInfo;
    
    
    public Files() {
		super();
	}
    

	public Files(Integer fileId, String fileName, Integer fileSize, Folder fileSuperFolder, String fileState,
			Date fileCreateDate, Date fileUpdateDate, byte[] fileInfo) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileSuperFolder = fileSuperFolder;
		this.fileState = fileState;
		this.fileCreateDate = fileCreateDate;
		this.fileUpdateDate = fileUpdateDate;
		this.fileInfo = fileInfo;
	}
	


	public Files(String fileName, Integer fileSize, Folder fileSuperFolder, String fileState, Date fileCreateDate,
			Date fileUpdateDate, byte[] fileInfo) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileSuperFolder = fileSuperFolder;
		this.fileState = fileState;
		this.fileCreateDate = fileCreateDate;
		this.fileUpdateDate = fileUpdateDate;
		this.fileInfo = fileInfo;
	}


	public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Folder getFileSuperFolder() {
        return fileSuperFolder;
    }

    public void setFileSuperFolder(Folder fileSuperFolder) {
        this.fileSuperFolder = fileSuperFolder;
    }

    public String getFileState() {
        return fileState;
    }

    public void setFileState(String fileState) {
        this.fileState = fileState == null ? null : fileState.trim();
    }

    public Date getFileCreateDate() {
        return fileCreateDate;
    }

    public void setFileCreateDate(Date fileCreateDate) {
        this.fileCreateDate = fileCreateDate;
    }

    public Date getFileUpdateDate() {
        return fileUpdateDate;
    }

    public void setFileUpdateDate(Date fileUpdateDate) {
        this.fileUpdateDate = fileUpdateDate;
    }

    public byte[] getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(byte[] fileInfo) {
        this.fileInfo = fileInfo;
    }


	@Override
	public String toString() {
		return "Files [fileId=" + fileId + ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileSuperFolder="
				+ fileSuperFolder + ", fileState=" + fileState + ", fileCreateDate=" + fileCreateDate
				+ ", fileUpdateDate=" + fileUpdateDate + ", fileInfo=" + Arrays.toString(fileInfo) + "]";
	}
    
}