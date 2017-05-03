package com.csjbot.robot.base.mybatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;

import com.csjbot.robot.base.util.PackageUtil;
import com.csjbot.robot.base.util.StringUtil;

/**
 * 支持通配符的Model匹配方式
 * @author Cjay
 *
 */
public class DummyMappedSqlSessionFactoryBean extends SqlSessionFactoryBean {

    private final static Logger log = Logger.getLogger(DummyMappedSqlSessionFactoryBean.class);

    /**
     * 默认的
     */
    private String defaultTypeAliasesPackage;

    @Override
    public void setTypeAliasesPackage(String typeAliasesPackage) {
        List<String> packages = PackageUtil.getPackages(typeAliasesPackage, true);
        // --------------------------------------------------------------------------------------------------------------------------------------
        String packageStr = "";
        for (int i = 0; i < packages.size(); i++) {
            packageStr += packages.get(i);
            if (i != packages.size() - 1) packageStr += ",";
        }
        // --------------------------------------------------------------------------------------------------------------------------------------
        if (packages.size() == 0) {
            log.warn("Parameter 'typeAliasesPackage': " + typeAliasesPackage + ", can not found any packages.");
            // --------------------------------------------------------------------------------------------------------------------------------------
            if (log.isDebugEnabled()) {
                log.info("Start to set parameter 'typeAliasesPackage' as 'defaultTypeAliasesPackage': " + defaultTypeAliasesPackage + ".");
            }
            if (StringUtil.isEmpty(packageStr)) packageStr = defaultTypeAliasesPackage;
        }
        // --------------------------------------------------------------------------------------------------------------------------------------
        super.setTypeAliasesPackage(packageStr);
    }

    public String getDefaultTypeAliasesPackage() {
        return defaultTypeAliasesPackage;
    }

    public void setDefaultTypeAliasesPackage(String defaultTypeAliasesPackage) {
        this.defaultTypeAliasesPackage = defaultTypeAliasesPackage;
    }

}
