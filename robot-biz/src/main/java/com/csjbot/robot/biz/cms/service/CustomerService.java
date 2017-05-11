package com.csjbot.robot.biz.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.base.exception.ServiceException;
import com.csjbot.robot.biz.cms.dao.CustomerDao;
import com.csjbot.robot.biz.cms.model.Customer;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;



@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Customer get(String code,String code_group)throws ServiceException{
		return customerDao.get(code, code_group);
		
	}
	
	public <K, V> Map<K, V> findOne(String code,String code_group)throws ServiceException{
		return customerDao.findOne(code, code_group);
	}
	
	public <T, K, V> List<T> find(Map<K, V> params)throws ServiceException{
		return customerDao.find(params);
	}
	
	public int insert(Customer customer){
		return customerDao.insert(customer);
	}
	
	public int update(Customer customer){
		return customerDao.update(customer);
	}
	
	public Customer selectByPrimaryKey(String code,String code_group){
		return customerDao.selectByPrimaryKey(code, code_group);
	}
	
	public int delete(String code,String code_group)throws ServiceException{
		return customerDao.delete(code, code_group);
	}
	
	public PageList<Customer> page(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return customerDao.page(params, pager);
    }

}
