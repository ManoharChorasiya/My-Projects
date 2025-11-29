package com.info.jpatest;

import java.util.Scanner;

import com.info.jpatest.entity.StudentCourse;
import com.info.jpatest.entity.StudentCourseKey;
import com.info.jpatest.entity.User;
import com.info.jpatest.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class App 
{
	
    public static void main( String[] args )
    
    {
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.println("press 1 for save ");
        System.out.println("press 2 for login");
        System.out.println("press 3 for fetch by id");
        System.out.println("press 4 for testing level cache");
        System.out.println("press 5 for insert student course");
        System.out.println("press 0 fpr exit ");
        System.out.println("enter your choice");
        int choice  = sc.nextInt();
     
        switch(choice) {
        case 1 : save();
        			break;
        case 2 : login();
        			break;
        case 3 :	 fetchById();
        			 break;
        case 4 :testCache();
        			break;
        case 5 :insertStudentCourse();
        			break;
        case 0 : System.exit(0);		
        }
    }
    }
	public static void insertStudentCourse() {
		EntityManagerFactory factory = JPAUtil.getFactory();
		try(EntityManager manager = factory.createEntityManager();){
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			
			StudentCourse sc = new StudentCourse();
			StudentCourseKey key = new StudentCourseKey();
			 key.setCourseId(120);
			 key.setStudentId(200);
			 
			 sc.setKey(key);
			 sc.setStudentCourse("java full stack ");
			 sc.setStudentName("Ram");
			 manager.persist(sc);
			 transaction.commit();
			 System.out.println("Record inserted...");
			 
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}

	}
	public static void testCache() {
		EntityManagerFactory factory1 = JPAUtil.getFactory();
		EntityManager manager1 = factory1.createEntityManager();
		User user = manager1.find(User.class,1);
		System.out.println(user.getEmail()+" "+user.getName());
		manager1.close();
		
		EntityManagerFactory factory2 = JPAUtil.getFactory();
		EntityManager manager2 = factory2.createEntityManager();
		User user2 = manager2.find(User.class, 1);
		System.out.println(user2.getEmail()+" "+user2.getName());
		manager2.close();
		
	}
	public static void fetchById() {
		EntityManagerFactory factory = JPAUtil.getFactory();
		try(EntityManager manager = factory.createEntityManager();){
		   User user = manager.find(User.class, 1);
		   System.out.println(user.getName()+" "+user.getAge());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void login() {
		EntityManagerFactory factory = JPAUtil.getFactory();
		EntityTransaction transaction = null;
		try(EntityManager manager = factory.createEntityManager();){
			 Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter email");
			String email = sc.next();
			
			System.out.println("Enter password");
			String password = sc.next();
			
			transaction = manager.getTransaction();
			transaction.begin();
			User user = new User();
			user.setEmail(email);
	
			user.setPassword(password);
			System.out.println("login succesfull");
				
		}
		catch(Exception e) {
			e.printStackTrace();
			if(transaction != null && transaction.isActive())
				transaction.rollback();
		}
	}

	public static void save() {
		EntityManagerFactory factory = JPAUtil.getFactory();
		EntityTransaction transaction = null;
		try(EntityManager manager = factory.createEntityManager();){
			Scanner sc = new Scanner(System.in);
			
				System.out.println("Enter name");
				String name = sc.next();
				
				System.out.println("Enter email");
				String email = sc.next();
				
				System.out.println("Enter password");
				String password = sc.next();
				
				System.out.println("Enter age");
				int age = sc.nextInt();
						
				transaction = manager.getTransaction();
				transaction.begin();
				User user = new User();
				user.setEmail(email);
				user.setAge(age);
				user.setName(name);
				user.setPassword(password);
				System.out.println("login succesfull");
					
			}
			catch(Exception e) {
				e.printStackTrace();
				if(transaction != null && transaction.isActive())
					transaction.rollback();
			}
			
		}
		
	}

