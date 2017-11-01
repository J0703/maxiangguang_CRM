package com.lanou.eduction.action;

import com.lanou.eduction.domain.Classes;
import com.lanou.eduction.domain.CourseType;
import com.lanou.common.domain.PageBean;
import com.lanou.eduction.service.ClassesService;
import com.lanou.eduction.service.CourseTypeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
@Controller("classesAction")
@Scope("prototype")
public class ClassesAction extends ActionSupport implements ModelDriven<Classes> {

    @Resource
    private ClassesService classesService;
    @Resource
    private CourseTypeService courseTypeService;

    private Classes classes;

    List<CourseType> courses;
    private String courseId;

    private PageBean<Classes> pageBean;
    private int pageNum;
    private int pageSize = 4;

    /**
     * 添加 和 编辑 班级
     *
     */
    public String addClass() {

        if ("".equals(classes.getClassName())) {
            addActionError("班级名称不能为空");
        }
        if ("-1".equals(classes.getChangeCount())) {
            addActionError("课程类型不能为空");
        }
        if ("".equals(classes.getBeginTime())) {
            addActionError("开课时间不能为空");
        }
        if ("".equals(classes.getEndTime())) {
            addActionError("结课时间不能为空");
        }
        if (classes.getBeginTime().getTime()>= classes.getEndTime().getTime()){
            addActionError("开始时间不能比结束时间早");
        }
        if ("".equals(classes.getClassName()) || "-1".equals(classes.getChangeCount()) || "".equals(classes.getBeginTime()) || "".equals(classes.getEndTime())||classes.getBeginTime().getTime()>= classes.getEndTime().getTime()) {
            return ERROR;
        }


        // 根据 classId 查询出 courseType , 赋值给 classes
        CourseType courseType = courseTypeService.findById(courseId);
        classes.setCourseType(courseType);


        if ("".equals(classes.getClassId()) || null == classes.getClassId()) {
            // 保存
            classesService.save(classes);
            return SUCCESS;

        }
        // 更新
        classesService.update(classes);
        return SUCCESS;

    }

    public String showCourse() {
        courses = courseTypeService.findAll();
        return SUCCESS;
    }

    /**
     * 查询所有
     *
     */
    public String findAll() {

        pageBean = classesService.findAll(classes, null, null, pageNum, pageSize);

        return SUCCESS;
    }

    /**
     *  查询classes, 回显 showClasses.jsp
     */
    public String showClasses(){

        classes = classesService.findById(classes.getClassId());
        return SUCCESS;
    }

    /**
     *  上传准备
     */
    public String preparedUpload(){
        // 回显
        classes = classesService.findById(classes.getClassId());

        return SUCCESS;
    }

    private File photo;  // 表单提交过来的文件
    private String photoFileName;  // 提交的文件对应的文件名
    private String photoContentType; // 提交的文件对应的格式

    /**
     *  文件上传
     */
    public String upload(){

        // 获取当前项目下的files路径
        String path = ServletActionContext.getServletContext().getRealPath("files");


        // 文件保存目录
        File destDirectory = new File(path);

        // 如果目的文件目录不存在, 则需要创建一个该目录
        if (!destDirectory.exists() || !destDirectory.isDirectory()){
            destDirectory.mkdirs();
        }


        // 构建一个空的文件对象, 用于存储上传的文件
        File file = new File(destDirectory, photoFileName);

        try {

            FileUtils.copyFile(photo, file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 数据库查询
        Classes byId = classesService.findById(classes.getClassId());

        // 赋值并更新该对象
        Date time = new Date();
        byId.setUploadTime(time);

        byId.setUploadPath(path);
        byId.setUploadFilename(photoFileName);

        classesService.update(byId);

        return SUCCESS;
    }

    private InputStream inputStream;
    private String fileName;  // 要下载的文件名称

    /**
     *  文件下载
     */
    public String download(){

        // 查询到员工
        classes = classesService.findById(classes.getClassId());

        // 获得下载路径
        String dirPath = ServletActionContext.getServletContext().getRealPath("files");

        fileName = classes.getUploadFilename();

        // 要下载的文价对象
        File file = new File(dirPath, fileName);

        try {
            // 构建输入流对象
            inputStream = new FileInputStream(file);

            /*
                将文件名称中包含中文的部分进行浏览器兼容处理, 放在inputStream赋值之后,
                动作方法返回之前
             */
            fileName = filenameEncoding(fileName,
                    ServletActionContext.getRequest(),
                    ServletActionContext.getResponse());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    /**
     *   解决下载文件中含有中文的问题
     * @throws IOException
     */
    private String filenameEncoding(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String agent = request.getHeader("User-Agent");   // System.out.println(agent);
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?"+ base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        } else if(agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else if (agent.contains("Safari")){
            filename = new String(filename.getBytes("UTF-8"),"ISO8859-1");
        }else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }


    @Override
    public Classes getModel() {
        classes = new Classes();
        return classes;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageBean<Classes> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<Classes> pageBean) {
        this.pageBean = pageBean;
    }

    public List<CourseType> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseType> courses) {
        this.courses = courses;
    }

    public CourseTypeService getCourseTypeService() {
        return courseTypeService;
    }

    public void setCourseTypeService(CourseTypeService courseTypeService) {
        this.courseTypeService = courseTypeService;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public ClassesService getClassesService() {
        return classesService;
    }

    public void setClassesService(ClassesService classesService) {
        this.classesService = classesService;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

}
