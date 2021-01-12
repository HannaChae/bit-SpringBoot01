package com.example.demo.uss.service;

import java.util.ArrayList;
import static java.util.Comparator.comparing;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.cmm.enm.Sql;
import com.example.demo.cmm.utl.DummyGenerator;
import com.example.demo.cmm.utl.Pagination;


import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StudentService{
	@Autowired DummyGenerator dummy;
    @Autowired
    StudentMapper studentMapper;
    

    @Transactional
    public int insertMany(int count) {
    	for(int i=0; i < count; i++) {
    		studentMapper.insert(dummy.makeStudent());
    	}
    	int a = count();
    	System.out.println("-------"+a);
    	return a;   	
    }

    @Transactional
	public int truncate() {
    	var map = new HashMap<String,String>();
    	map.put("TRUNCATE_STUDENTS", Sql.TRUNCATE_STUDENTS.toString());
    	studentMapper.truncate(map);
    	return count() != 0 ? 0 : 1;
	}
	
	public int count() {
    	var map = new HashMap<String,String>();
    	map.put("COUNT", Sql.COUNT.toString() + "students");
    	return studentMapper.count(map);
	}
	
	public List<Student> list(Pagination page){
		return studentMapper.list().stream()
				.sorted(comparing(Student::getStuNum).reversed())
				.skip(page.getPageSize() * page.getPageNum()-1)
				.limit(page.getPageSize())
				.collect(Collectors.toList());
	}
	/*
	public List<Student> selectAll(){
    	var map = new HashMap<String,String>();
    	map.put("SELECT_ALL_STUDENTS", Sql.SELECT_ALL_STUDENTS.toString());
		return studentMapper.selectAll(map);
	}

	public List<Student> selectByGender(String gender){		
		return selectAll().stream()
				
				.collect(toList());
	}
	*/
}