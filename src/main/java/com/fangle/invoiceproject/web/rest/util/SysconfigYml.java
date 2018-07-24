package com.fangle.invoiceproject.web.rest.util;

import org.yaml.snakeyaml.Yaml;

/**
 * <p>Title: 2018/7/20 0020</p>
 * <p>Description: com.fangle.invoiceproject.web.rest.util</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 方格尔科技</p>*
 *
 * @author 作者: duych
 * @version 创建时间：下午 7:41
 */
public class SysconfigYml {

    private ZsinvoiceProject zsinvoiceProject;
    public ZsinvoiceProject getZsinvoiceProject() {
        return zsinvoiceProject;
    }

    public void setZsinvoiceProject(ZsinvoiceProject zsinvoiceProject) {
        this.zsinvoiceProject = zsinvoiceProject;
    }


    public static SysconfigYml config = null;
    /**
     * 获取系统参数，系统参数比应用参数多几个与生产和调试模式无关的参数
     * @return
     */
    public static SysconfigYml get() {
        if (config == null) {
            synchronized (SysconfigYml.class) {
                Yaml yaml = new Yaml();
                SysconfigYml patthSnake = new SysconfigYml();;
                config = yaml.loadAs(patthSnake.getClass().getResourceAsStream("/Sysconfig.yaml"), SysconfigYml.class);
            }
        }
        return config;
    }
}
