package sgg.qin.util;

import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * 
 * @Description: 定时作业管理类
 * @author: Qin YunFei
 * @date: 2017年11月6日 下午8:23:13
 * @version V1.0
 */
public class QuartzUtil {
	public static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory(); // 创建一个SchedulerFactory工厂实例
	public static String JOB_GROUP_NAME = "作业组一"; // 作业组
	public static String TRIGGER_GROUP_NAME = "触发器组一"; // 触发器组

	/*
	 * 必须搞清楚 jobKey和triggerKey分别为作业和触发器的身份证
	 * 注意一个作业（jobKey）只可以被添加到所述调度器一次并且必须和触发器器一起添加 一起添加时调度器会为触发器绑定该作业 触发器可以单独添加到调度器
	 * 单独添加调度器时 该触发器必须自己绑定作业（jobKey） 一个作业可以有多个触发器（triggerKey）
	 * 一个触发器（triggerKey）只能被添加到所属调度器一次
	 * 
	 */

	/**
	 * 添加一个定时作业，使用默认的作业组名,触发器组名
	 * 
	 * @param jobName
	 *            作业名
	 * @param triggerName
	 *            触发器名
	 * @param jobClass
	 *            作业
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 */
	public static void addJob(String jobName, String triggerName, Class<? extends Job> jobClass, String cron) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);

			// 创建作业
			JobDetail jobDetail = QuartzHelp.createJobDetail(jobKey, jobClass);
			; // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
				// 创建触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加一个定时作业，使用默认的作业组名，触发器名，触发器组名 （触发器带参数）
	 * 
	 * @param jobName
	 *            作业名
	 * @param triggerName
	 *            触发器名
	 * @param cls
	 *            作业
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 * @param parameter
	 *            参数map
	 */
	public static void addJob(String jobName, String triggerName, Class<? extends Job> jobClass, String cron,
			Map<String, Object> parameter) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);

			// 创建作业
			JobDetail jobDetail = QuartzHelp.createJobDetail(jobKey, jobClass);
			; // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
				// 创建带参数带触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron, parameter);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加一个定时作业
	 * 
	 * @param jobName
	 *            作业名
	 * @param jobGroupName
	 *            作业组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            作业
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 */
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			Class<? extends Job> jobClass, String cron) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

			// 创建作业
			JobDetail jobDetail = QuartzHelp.createJobDetail(jobKey, jobClass);
			; // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
				// 创建触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加一个定时作业 （触发器带参数）
	 * 
	 * @param jobName
	 *            作业名
	 * @param jobGroupName
	 *            作业组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            作业
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 * @param parameter
	 *            参数map
	 */
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			Class<? extends Job> jobClass, String cron, Map<String, Object> parameter) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

			// 创建作业
			JobDetail jobDetail = QuartzHelp.createJobDetail(jobKey, jobClass);
			; // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
				// 创建触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron, parameter);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 为一个作业添加触发器， 前提为该作业必须在调度器中注册过
	 * 
	 * @param jobName
	 *            作业名
	 * @param jobGroupName
	 *            作业组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 */
	public static void addTrigger(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			String cron) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			// 创建触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 为一个作业添加触发器，(带参数) 前提为该作业必须在调度器中注册过
	 * 
	 * @param jobName
	 *            作业名
	 * @param jobGroupName
	 *            作业组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 * @param parameter
	 *            参数map
	 */
	public static void addTrigger(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			String cron, Map<String, Object> parameter) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			// 创建触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron, parameter);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 为一个作业添加触发器，使用默认的作业组名,触发器组名 前提为该作业必须在调度器中注册过
	 * 
	 * @param jobName
	 *            作业名
	 * @param triggerName
	 *            触发器名
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 */
	public static void addTrigger(String jobName, String triggerName, String cron) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
			// 创建触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 为一个作业添加触发器，使用默认的作业组名,触发器组名(带参数) 前提为该作业必须在调度器中注册过
	 * 
	 * @param jobName
	 *            作业名
	 * @param triggerName
	 *            触发器名
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 * @param parameter
	 *            参数map
	 */
	public static void addTrigger(String jobName, String triggerName, String cron, Map<String, Object> parameter) {
		try {
			// 作业的JobKey
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
			// 触发器的triggerKey
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
			// 创建触发器
			CronTrigger trigger = (CronTrigger) QuartzHelp.createTrigger(jobKey, triggerKey, cron, parameter);

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler(); // 通过SchedulerFactory构建Scheduler对象
			// 注册作业和触发器
			sched.scheduleJob(trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改一个触发器的触发时间(使用默认的触发器组名)
	 * 
	 * @param jobName
	 *            作业名
	 * @param newcron
	 *            新的时间设置
	 */
	public static void modifyTriggerTime(String triggerName, String newcron) {
		// 创建TriggerKey
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);

		try {

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler();
			// 获取指定触发器
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				throw new NullPointerException(triggerKey + "不存在");
			}

			if (!trigger.getCronExpression().equalsIgnoreCase(newcron)) {
				JobKey jobKey = trigger.getJobKey();
				// 获取原触发器的构造器设置新的时间
				CronTrigger newbuild = trigger.getTriggerBuilder()
						.withSchedule(CronScheduleBuilder.cronSchedule(newcron)).build();

				// 判断目前调度器的该作业所有触发器
				@SuppressWarnings("unchecked")
				List<CronTrigger> list = (List<CronTrigger>) sched.getTriggersOfJob(jobKey);

				// 注意如果作业只有一个触发器 删除触发器的同时会同时删除作业 所以这里要进行判断
				if (list.size() > 1) {
					// 移除触发器指定触发器
					sched.unscheduleJob(triggerKey);
					// 注册触发器
					sched.scheduleJob(newbuild);

				} else {
					JobDetail jobDetail = sched.getJobDetail(jobKey);
					// 移除触发器指定触发器
					sched.unscheduleJob(triggerKey);
					// 注册作业与触发器
					sched.scheduleJob(jobDetail, newbuild);

				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改一个触发器的触发时间
	 * 
	 * @param triggerName
	 *            触发器名称
	 * @param triggerGroupName
	 *            触发器名称分组名称
	 * @param time
	 *            更新后的时间规则
	 */
	public static void modifyTriggerTime(String triggerName, String triggerGroupName, String newcron) {
		// 创建TriggerKey
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

		try {

			// 获取调度器
			Scheduler sched = gSchedulerFactory.getScheduler();
			// 获取指定触发器
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				throw new NullPointerException(triggerKey + "不存在");
			}

			if (!trigger.getCronExpression().equalsIgnoreCase(newcron)) {
				JobKey jobKey = trigger.getJobKey();
				// 获取原触发器的构造器设置新的时间
				CronTrigger newbuild = trigger.getTriggerBuilder()
						.withSchedule(CronScheduleBuilder.cronSchedule(newcron)).build();

				// 判断目前调度器的该作业所有触发器
				@SuppressWarnings("unchecked")
				List<CronTrigger> list = (List<CronTrigger>) sched.getTriggersOfJob(jobKey);

				// 注意如果作业只有一个触发器 删除触发器的同时会同时删除作业 所以这里要进行判断
				if (list.size() > 1) {
					// 移除触发器指定触发器
					sched.unscheduleJob(triggerKey);
					// 注册触发器
					sched.scheduleJob(newbuild);

				} else {
					JobDetail jobDetail = sched.getJobDetail(jobKey);
					// 移除触发器指定触发器
					sched.unscheduleJob(triggerKey);
					// 注册作业与触发器
					sched.scheduleJob(jobDetail, newbuild);

				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除指定触发器(默认的触发器组名)
	 * 
	 * @param triggerName
	 *            触发器名称
	 */
	public void removeTrigger(String triggerName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
		QuartzHelp.unscheduleJob(triggerKey);
	}

	/**
	 * 删除指定触发器
	 * 
	 * @param triggerName
	 *            触发器名称
	 * @param triggerGroupName
	 *            触发器名称分组名称
	 */
	public void removeTrigger(String triggerName, String triggerGroupName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
		QuartzHelp.unscheduleJob(triggerKey);
	}

	/**
	 * 暂停指定触发器(默认的触发器组名)
	 * 
	 * @param triggerName
	 *            触发器名称
	 */
	public void pauseTrigger(String triggerName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
		QuartzHelp.pauseTrigger(triggerKey);
	}

	/**
	 * 暂停指定触发器(默认的触发器组名)
	 * 
	 * @param triggerName
	 *            触发器名称
	 * @param triggerGroupName
	 *            触发器名称分组名称
	 */
	public void pauseTrigger(String triggerName, String triggerGroupName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
		QuartzHelp.pauseTrigger(triggerKey);
	}

	/**
	 * 恢复指定触发器(默认的触发器组名)
	 * 
	 * @param triggerName
	 *            触发器名称
	 */
	public void reinstateTrigger(String triggerName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
		QuartzHelp.resumeTrigger(triggerKey);
	}

	/**
	 * 恢复指定触发器(默认的触发器组名)
	 * 
	 * @param triggerName
	 *            触发器名称
	 * @param triggerGroupName
	 *            触发器名称分组名称
	 */
	public void reinstateTrigger(String triggerName, String triggerGroupName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
		QuartzHelp.resumeTrigger(triggerKey);
	}

	/**
	 * 移除一个作业(使用默认的作业组名) 注移除作业的同时会移除绑定该作业的所有触发器
	 * 
	 * @param jobName
	 *            作业名称
	 */
	public static void deleteJob(String jobName) {
		// 通过作业名和组名获取JobKey
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		QuartzHelp.deleteJob(jobKey);
	}

	/**
	 * 移除一个作业
	 * 
	 * @param jobName
	 *            作业名
	 * @param jobGroupName
	 *            作业组名
	 */

	public static void deleteJob(String jobName, String jobGroupName) {
		// 通过作业名和组名获取JobKey
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		QuartzHelp.deleteJob(jobKey);
	}

	/**
	 * 判断作业是否在调度器中注册过(使用默认的作业组名)
	 * 
	 * @param jobName
	 *            作业名称
	 */
	public static Boolean isJobKeyToScheduler(String jobName) {
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		return QuartzHelp.isJobKeyToScheduler(jobKey);
	}

	/**
	 * 判断作业是否在调度器中注册过
	 * 
	 * @param jobName
	 *            作业名
	 * @param jobGroupName
	 *            作业组名
	 */
	public static Boolean isJobKeyToScheduler(String jobName, String jobGroupName) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		return QuartzHelp.isJobKeyToScheduler(jobKey);
	}

	/**
	 * 
	 * 判断触发器是否在调度器中注册过（使用默认的触发器组名）
	 * 
	 * @param triggerName
	 *            触发器名
	 */
	public static Boolean isTriggerToScheduler(String triggerName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, TRIGGER_GROUP_NAME);
		return QuartzHelp.isTriggerToScheduler(triggerKey);
	}

	/**
	 * 
	 * 判断触发器是否在调度器中注册过
	 * 
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 */
	public static Boolean isTriggerToScheduler(String triggerName, String triggerGroupName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
		return QuartzHelp.isTriggerToScheduler(triggerKey);

	}

	// 列出所有Quartz的作业状态（测试用）
	public static void showlist() throws SchedulerException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

		List<String> jobGroupNames = scheduler.getJobGroupNames();

		for (String string : jobGroupNames) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(string))) {
				System.out.println();
				System.out.println("-------同一jobKey开始-------");
				List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggersOfJob) {
					TriggerKey triggerKey = trigger.getKey();
					System.out.println(jobKey + ">>>>>>>>>" + triggerKey);
				}
				System.out.println("-------同一jobKey结束-------");
			}
		}

	}

	/**
	 * 启动所有定时作业
	 */
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭所有定时作业
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 简单封装作为QuartzUtil 的工具类
	private static class QuartzHelp {

		/**
		 * 移除一个作业
		 * 
		 * @param jobKey
		 *            作业KEY
		 */
		public static void deleteJob(JobKey jobKey) {
			try {

				// 获取调度器
				Scheduler sched = gSchedulerFactory.getScheduler();
				// 删除作业 的同时会删除所有的触发器
				sched.deleteJob(jobKey);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 
		 * Description : 暂停触发器 <br>
		 * ParamKeys :() <br>
		 * return : void
		 */
		public static void pauseTrigger(TriggerKey triggerKey) {
			try {
				Scheduler sched = gSchedulerFactory.getScheduler();
				sched.pauseTrigger(triggerKey); // 暂停触发器
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 
		 * Description : 恢复触发器 <br>
		 * ParamKeys :() <br>
		 * return : void
		 */
		public static void resumeTrigger(TriggerKey triggerKey) {
			try {
				Scheduler sched = gSchedulerFactory.getScheduler();
				sched.unscheduleJob(triggerKey);// 移除触发器
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 
		 * Description : 移除触发器 <br>
		 * ParamKeys :() <br>
		 * return : void
		 */
		public static void unscheduleJob(TriggerKey triggerKey) {
			try {
				Scheduler sched = gSchedulerFactory.getScheduler();
				sched.unscheduleJob(triggerKey);// 移除触发器
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 
		 * Description : 创建作业 <br>
		 * ParamKeys :() <br>
		 * return : JobDetail
		 */
		public static JobDetail createJobDetail(JobKey jobKey, Class<? extends Job> jobClass) {
			JobDetail jobDetailt = JobBuilder.newJob(jobClass).withIdentity(jobKey).build();
			return jobDetailt;
		}

		/**
		 * 
		 * Description : 判断作业是否在调度器中注册过<br>
		 * ParamKeys :(JobKey:作业key) <br>
		 * return : void
		 */
		public static Boolean isJobKeyToScheduler(JobKey jobKey) {
			boolean checkExists = false;
			// 获取调度器
			try {
				Scheduler sched = gSchedulerFactory.getScheduler();
				checkExists = sched.checkExists(jobKey);
			} catch (SchedulerException e) {
				e.printStackTrace();
			} // 通过SchedulerFactory构建Scheduler对象

			return checkExists;
		}

		/**
		 * 
		 * Description : 判断触发器是否在调度器中注册过<br>
		 * ParamKeys :(TriggerKey:触发器key) <br>
		 * return : void
		 */
		public static Boolean isTriggerToScheduler(TriggerKey triggerKey) {
			boolean checkExists = false;
			// 获取调度器
			try {
				Scheduler sched = gSchedulerFactory.getScheduler();
				checkExists = sched.checkExists(triggerKey);
			} catch (SchedulerException e) {

				e.printStackTrace();
			} // 通过SchedulerFactory构建Scheduler对象

			return checkExists;
		}

		/**
		 * 
		 * Description : 创建带参数作业 注很少有作业带参数 所以下面使用的都是触发器带参数 <br>
		 * ParamKeys :() <br>
		 * return : JobDetail
		 */
		public static JobDetail createJobDetail(JobKey jobKey, Class<? extends Job> jobClass, Map<String, Object> map) {
			JobDetail jobDetailt = JobBuilder.newJob(jobClass).withIdentity(jobKey).usingJobData(new JobDataMap(map))
					.build();
			return jobDetailt;
		}

		/**
		 * 
		 * Description : 创建触发器 <br>
		 * ParamKeys :() <br>
		 * return : Trigger
		 */
		public static Trigger createTrigger(JobKey jobKey, TriggerKey triggerKey, String cron) {
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow().forJob(jobKey)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)// 时间
					).build();
			return trigger;
		}

		/**
		 * 
		 * Description : 创建带参数的触发器 <br>
		 * ParamKeys :() <br>
		 * return : Trigger
		 */
		public static Trigger createTrigger(JobKey jobKey, TriggerKey triggerKey, String cron,
				Map<String, Object> map) {
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow().forJob(jobKey)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)// 时间
					).usingJobData(new JobDataMap(map)).build();
			return trigger;
		}

	}

}