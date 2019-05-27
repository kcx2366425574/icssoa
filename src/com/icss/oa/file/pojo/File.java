package com.icss.oa.file.pojo;

import java.sql.Date;

import com.icss.oa.folder.pojo.Folder;


public class File {
    private Integer fileId;

    private String fileName;

    private String fileInfo;

    private Integer fileSize;

    private Folder fileSuperFolder;//many to one

    private String fileState;

    private Date fileCreateDate;

    private Date fileUpdateDate;

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
		this.fileName = fileName;
	}

	public String getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
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
		this.fileState = fileState;
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

	public File() {
		super();
	}

	public File(Integer fileId, String fileName, String fileInfo, Integer fileSize, Folder fileSuperFolder,
			String fileState, Date fileCreateDate, Date fileUpdateDate) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileInfo = fileInfo;
		this.fileSize = fileSize;
		this.fileSuperFolder = fileSuperFolder;
		this.fileState = fileState;
		this.fileCreateDate = fileCreateDate;
		this.fileUpdateDate = fileUpdateDate;
	}

	public File(String fileName, String fileInfo, Integer fileSize, Folder fileSuperFolder, String fileState,
			Date fileCreateDate, Date fileUpdateDate) {
		super();
		this.fileName = fileName;
		this.fileInfo = fileInfo;
		this.fileSize = fileSize;
		this.fileSuperFolder = fileSuperFolder;
		this.fileState = fileState;
		this.fileCreateDate = fileCreateDate;
		this.fileUpdateDate = fileUpdateDate;
	}

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileName=" + fileName + ", fileInfo=" + fileInfo + ", fileSize=" + fileSize
				+ ", fileSuperFolder=" + fileSuperFolder + ", fileState=" + fileState + ", fileCreateDate="
				+ fileCreateDate + ", fileUpdateDate=" + fileUpdateDate + "]";
	}

   
    
   

	
}