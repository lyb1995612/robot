package com.csjbot.robot.admin.controller.sys;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csjbot.robot.biz.base.exception.ServiceException;
import com.csjbot.robot.biz.base.entity.ResultEntity;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.sys.model.SysMenu;
import com.csjbot.robot.biz.sys.service.SysMenuService;
import com.csjbot.robot.biz.ums.model.User;

@Controller
@RequestMapping("/menu")
public class SysMenuController {
    
    private Logger logger = Logger.getLogger(SysMenuController.class);
    @Autowired
    private SysMenuService sysMenuService;
    
    /**
     * 菜单显示页面
     * @param model
     * @return
     */
    @RequestMapping("/toListMenus")
    public String toListMenus(Model model){
        
        return "sys/sys_menu_view";
    }
    
    /**
     * 菜单树形结构
     * @param response
     * @return
     */
    @RequestMapping(value = "/listMenus", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> listMenus(HttpServletResponse response, HttpServletRequest request){
        JSONObject result = new JSONObject();
        String msg = "";
        JSONObject content = new JSONObject();
        String path = request.getContextPath();
        try {
            Subject subject = SecurityUtils.getSubject();
            boolean idPermitted = subject.isPermittedAll("modify:menu_management");
            List<SysMenu> menus = sysMenuService.listAll(null);
            if (menus != null) {
                String operation = "<td><a href='"+path+"/menu/%s/toMenuDetail'>详情</a><i style=\"width: 20px;display: inline-block;\"></i><a href='"+path+"/menu/%s/toMenuEdit'>编辑</a></td>";
                String operation2 = "<td><a href='"+path+"/menu/%s/toMenuDetail'>详情</a><i style=\"width: 20px;display: inline-block;\"></i></td>";
                String detail = "<td><i style=\"width: 100px;display: inline-block;\"></i>排序 : %s, ID : %s</td>";
                String item = "";
                String folder = "";
                String subitem = "";
                if (idPermitted) {
                    item = "<table style='display: inline-block;'><tr><td style='width: 215px;'><i class=\"icon-file-text grey\"></i> %s</td>" + operation + detail + "</tr></table>";
                    folder = "<table style='display: inline-block;'><tr><td style='width: 200px;'> %s</td>" + operation + detail + "</tr></table>";
                    subitem = "<table style='display: inline-block;'><tr><td style='width: 245px;'><i class=\"icon-file-text blue\"></i> %s</td>" + operation + detail + "</tr></table>";
                } else {
                    item = "<table style='display: inline-block;'><tr><td style='width: 215px;'><i class=\"icon-file-text grey\"></i> %s</td>" + operation2 + detail + "</tr></table>";
                    folder = "<table style='display: inline-block;'><tr><td style='width: 200px;'> %s</td>" + operation2 + detail + "</tr></table>";
                    subitem = "<table style='display: inline-block;'><tr><td style='width: 245px;'><i class=\"icon-file-text blue\"></i> %s</td>" + operation2 + detail + "</tr></table>";
                }
               
                int size = menus.size();
                Map<String, Object> params = new HashMap<String, Object>();
                for (int i = 0; i < size; i++) {
                    SysMenu menu_1 = menus.get(i);
                    String level_1 = menu_1.getLevel();
                    String id_1 = menu_1.getId();
                    if ("1".equals(level_1) ) {
                        String url = menu_1.getUrl();
                        String name = menu_1.getName();
                        List<Map<String, Object>> childrenList = new ArrayList<Map<String, Object>>();
                        for (int j = 0; j < size; j++) {
                            SysMenu menu_2 = menus.get(j);
                            String level_2 = menu_2.getLevel();
                            if ("2".equals(level_2) && id_1.equals(menu_2.getParent_fk())) {
                                Map<String, Object> child = new HashMap<String, Object>();
                                if (idPermitted) {
                                    child.put("name", String.format(subitem, menu_2.getName(), menu_2.getId(), menu_2.getId(), menu_2.getSorter(), menu_2.getActiveId() ));
                                } else {
                                    child.put("name", String.format(subitem, menu_2.getName(), menu_2.getId(), menu_2.getSorter(), menu_2.getActiveId() ));
                                }
                                
                                child.put("type", "item");
                                childrenList.add(child);
                            } else {
                                continue;
                            }
                        }
                        
                        if (url != null && !"".equals(url ) ) {
                            if (idPermitted) {
                                params.put("name", String.format(item, name, id_1, id_1, menu_1.getSorter(), menu_1.getActiveId()));
                            } else {
                                params.put("name", String.format(item, name, id_1, menu_1.getSorter(), menu_1.getActiveId()));
                            }
                            params.put("type", "item");
                        } else {
                            if (idPermitted) {
                                params.put("name", String.format(folder, name, id_1, id_1, menu_1.getSorter(), menu_1.getActiveId()));
                            } else {
                                params.put("name", String.format(folder, name, id_1, menu_1.getSorter(), menu_1.getActiveId()));
                            }
                            params.put("type", "folder");
                            params.put("icon-class", "orange");
                            Map<String, Object> children = new HashMap<String, Object>();
                            children.put("children", childrenList);
                            params.put("additionalParameters", children);
                        }
                        content.put(String.valueOf(i), params);
                        params.clear();
                    } else {
                        continue;
                    }
                }
            }
            msg = ResultEntity.KW_STATUS_SUCCESS;
        } catch (ServiceException e) {
            msg = ResultEntity.KW_STATUS_FAIL;
            logger.error(e);
        }
        result.put("msg", msg);
        result.put("content", content);
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text","html",Charset.forName("utf-8"));
        headers.setContentType(mediaType);
        
       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);*/
        
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
        
    }
    
    /**
     * 菜单详情
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/{id}/toMenuDetail")
    public String toMenuDetail(Model model, @PathVariable String id){
        SysMenu menu = sysMenuService.findOne(id);
        SysMenu parent = sysMenuService.findOne(menu.getParent_fk());
        Map<String, Object> permission = sysMenuService.getMenuPermissionById(menu.getPermission_fk());
        
        model.addAttribute("menu", menu);
        model.addAttribute("parent", parent);
        model.addAttribute("permission", permission);
        return "sys/sys_menu_detail";
    }
    /**
     * 菜单编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/{id}/toMenuEdit")
    public String toMenuEdit(Model model, @PathVariable String id){
        SysMenu menu = sysMenuService.findOne(id);
        List<Map<String, Object>> permissions = sysMenuService.getMenuPermissions();
        List<Map<String, Object>> parents = sysMenuService.getMenuParents();
        
        model.addAttribute("menu", menu);
        model.addAttribute("permissions", permissions);
        model.addAttribute("parents", parents);
        return "sys/sys_menu_edit";
    }
    
    /**
     * 菜单编辑操作
     * @param model
     * @return
     */
    @RequestMapping(value = "/editMenu", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> editMenu(@ModelAttribute SysMenu sysMenu, HttpServletResponse response, HttpServletRequest request){
        JSONObject result = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        String msg = "";
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", sysMenu.getName());
            params.put("style", sysMenu.getStyle());
            params.put("parent_fk", sysMenu.getParent_fk());
            params.put("permission_fk", sysMenu.getPermission_fk());
            params.put("url", sysMenu.getUrl());
            params.put("level", sysMenu.getLevel());
            params.put("sorter", sysMenu.getSorter());
            params.put("activeId", sysMenu.getActiveId());
            params.put("valid", sysMenu.getValid());
            params.put("updater_fk", loginUser.getId());
            params.put("id", sysMenu.getId());
            sysMenuService.update(params);
            msg = ResultEntity.KW_STATUS_SUCCESS;
        } catch (ServiceException e) {
            msg = ResultEntity.KW_STATUS_FAIL;
            logger.error(e);
        }
        result.put("msg", msg);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
    }
    
    /**
     * 增加菜单页面
     * @param model
     * @return
     */
    @RequestMapping("/toAddMenu")
    public String toAddMenu(Model model){
        
        List<Map<String, Object>> permissions = sysMenuService.getMenuPermissions();
        List<Map<String, Object>> parents = sysMenuService.getMenuParents();
        
        model.addAttribute("permissions", permissions);
        
        model.addAttribute("parents", parents);
        
        return "sys/sys_menu_add";
    }
    
    /**
     * 增加菜单操作
     * @param model
     * @return
     */
    @RequestMapping(value = "/addMenu", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> addMenu(@ModelAttribute SysMenu sysMenu, HttpServletResponse response, HttpServletRequest request){
        JSONObject result = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        sysMenu.setUpdater_fk(loginUser.getId());
        sysMenu.setCreator_fk(loginUser.getId());
        String msg = "";
        try {
            sysMenuService.insert(sysMenu);
            msg = ResultEntity.KW_STATUS_SUCCESS;
        } catch (ServiceException e) {
            msg = ResultEntity.KW_STATUS_FAIL;
            logger.error(e);
        }
        result.put("msg", msg);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
    }
   
    /********************************************************************************************************/
    /********************************************************************************************************/
    /********************************************************************************************************/
    
    /**
     * 左侧菜单展示
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/showMenus", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> showMenu( HttpServletResponse response, HttpServletRequest request){
        JSONObject result = new JSONObject();
        String msg = "";
        String path = request.getContextPath();
        StringBuffer buffer = new StringBuffer();
        String content = "";
        try {
            Subject subject = SecurityUtils.getSubject();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("valid", 1);
            List<SysMenu> menuList = sysMenuService.listAll(params);
            String item = "<li id=\"%s\" class='menu-item'><a href='"+path+"%s' ><i class=\"%s\"></i><span class=\"menu-text\"> %s </span></a></li>";
            String folder = "<li id=\"%s\"><a href=\"#\" class=\"dropdown-toggle\"><i class=\"%s\"></i><span class=\"menu-text\"> %s</span><b class=\"arrow icon-angle-down\"></b></a>";
            String subitem = "<li id=\"%s\"><a href='"+path+"%s'><i class=\"icon-double-angle-right\"></i> %s</a></li>";
            if (menuList != null && menuList.size() > 0 ) {
                int size = menuList.size();
                for (int i = 0; i < size; i++) {
                    SysMenu menu_1 = menuList.get(i);
                    String level_1 = menu_1.getLevel();
                    String id_1 = menu_1.getId();
                    String permission_fk = menu_1.getPermission_fk();
                    if ( !checkMenuPermission(subject, permission_fk )) {
                        continue;
                    }
                    if ("1".equals(level_1) ) {
                        String url = menu_1.getUrl();
                        StringBuffer childrens = new StringBuffer();
                        for (int j = 0; j < size; j++) {
                            SysMenu menu_2 = menuList.get(j);
                            String level_2 = menu_2.getLevel();
                            if ("2".equals(level_2) && id_1.equals(menu_2.getParent_fk())) {
                                String permission_fk_2 = menu_2.getPermission_fk();
                                if ( !checkMenuPermission(subject, permission_fk_2)) {
                                    continue;
                                }
                                childrens.append(String.format(subitem, menu_2.getActiveId(), menu_2.getUrl(), menu_2.getName()));
                            } 
                        }
                        
                        if (url != null && !"".equals(url ) ) {
                            buffer.append(String.format(item, menu_1.getActiveId(), menu_1.getUrl(), menu_1.getStyle(), menu_1.getName()));
                        } else {
                            buffer.append(String.format(folder, menu_1.getActiveId(), menu_1.getStyle(), menu_1.getName()))
                                  .append("<ul class=\"submenu\">").append(childrens.toString()).append("</ul></li>");
                        }
                    }
                }
            }
            User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
            String menuManager = "<li id=\"sys_menu\" class='menu-item'><a href='"+path+"/menu/toListMenus' ><i class=\"icon-cog\"></i><span class=\"menu-text\"> 菜单管理 </span></a></li>";
            if (Constants.ADMIN_ACCOUNT.equals(loginUser.getUsername())) {
                buffer.append(menuManager);
            }
            content = buffer.toString();
            msg = ResultEntity.KW_STATUS_SUCCESS;
        } catch (ServiceException e) {
            msg = ResultEntity.KW_STATUS_FAIL;
            logger.error(e);
        }
        result.put("msg", msg);
        result.put("content", content);
        
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text","html",Charset.forName("utf-8"));
        headers.setContentType(mediaType);
        
        
       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);*/
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
    }
    
    /**
     * 返回true 代表有权限， false代表无权�?
     * @param subject
     * @param permission_fk
     * @return
     */
    private boolean checkMenuPermission(Subject subject, String permission_fk ){
        boolean flag = true;
        if (permission_fk != null && !"".equals(permission_fk)) {
            Map<String, Object> permission = sysMenuService.getMenuPermissionById(permission_fk);
            String perm_code = permission != null ? String.valueOf(permission.get("code")) : null;
            if(perm_code != null && !subject.isPermittedAll(perm_code)) {
                flag = false;
            } 
        }
        return flag;
    }
}   
