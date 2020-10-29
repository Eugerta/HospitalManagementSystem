package system.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system.model.InDoorVisitSchedule;
import system.model.OutDoorVisitSchedule;

public interface OutDoorVisitScheduleRepository extends JpaRepository<OutDoorVisitSchedule,Integer> {
	
	
	@Query(value ="select * from visit_schedule where doctor_id =?1",nativeQuery = true)
	public List<OutDoorVisitSchedule> findVisitsByDoctor(String doctor);
	
	@Query(value ="select * from visit_schedule where doctor_id =?1 and (date > DATE(now())  OR (date = DATE(now()) and time> TIME(now()))) ",nativeQuery = true)
	public List<OutDoorVisitSchedule> findCommingVisitsByDoctor(String doctor);
	
	@Query(value ="select * from visit_schedule where date =?1",nativeQuery = true)
	public List<OutDoorVisitSchedule> todayVisits(Date date);
	
	@Query(value ="select * from visit_schedule where date =?1 and doctor_id=?2",nativeQuery = true)
	public List<OutDoorVisitSchedule> todayDoctorVisits(Date date, String doctor);
	
	@Query(value ="delete from visit_schedule where doctor_id =?1",nativeQuery = true)
	void deleteVisitsByDoctor(String doctor);

	@Query(value ="delete from visit_schedule where patient_id =?1",nativeQuery = true)
	void deleteVisitsByPatient(String patient);
	
	@Query(value ="SELECT  date from visit_schedule where doctor_id =?1 and   date >= DATE(now()) GROUP by date  HAVING count(time)< 15 order by date  LIMIT 1",nativeQuery = true)
	public Date getNextFreeDateByDoctor(String doctorId) ;
	
	@Query(value ="SELECT  time from visit_schedule where doctor_id =?1 and   date =?2",nativeQuery = true)
	public List<Time> getTimeVisitByDoctor(String doctorId, Date date) ;
}
