select database();
use CSCI5308_15_DEVINT;

select * from `role`;
select * from `user`;
select * from `user_x_role`;

select * from `course`;
select * from `programs`;
select * from `course_by_term`;
SELECT * FROM `program_by_term`;
select * from `student_details`;
select * from `university_term`;
select * from `student_course_enrollment`;

#student
##student-course mapping (registration from UI)
##course seats table
##professor-course map
##student_course_enrollment_by_term

INSERT INTO `role` VALUES (5,'student','student priveleges');

INSERT INTO `user` VALUES (4,'Rishi','Vasa','rishi@dal.ca','rv33','rv123', 'Y');
INSERT INTO `user` VALUES (5,'John','Doe','jdoe@dal.ca','jdoe','jdoe', 'Y');

INSERT INTO `user_x_role` VALUES ('rv33','ADMIN');
INSERT INTO `user_x_role` VALUES ('jdoe','STUDENT');

INSERT INTO `CSCI5308_15_DEVINT`.`programs` (`program_id`, `program_name`, `program_level`) VALUES ('MACS','Master of Applied Computer Science','Graduate');
INSERT INTO `CSCI5308_15_DEVINT`.`programs` (`program_id`, `program_name`, `program_level`) VALUES ('BACS','Bachelor of Applied Computer Science','Undergraduate');
INSERT INTO `CSCI5308_15_DEVINT`.`programs` (`program_id`, `program_name`, `program_level`) VALUES ('MDI','Master of Digital Innovation','Graduate');

INSERT INTO `CSCI5308_15_DEVINT`.`student_details` (`student_user_id`, `student_number`, `student_name`, `student_program`) VALUES ('5', 'B00901111', 'John Doe', 'MACS');

INSERT INTO `CSCI5308_15_DEVINT`.`university_term` (`term_id`, `term_name`, `term_start_date`, `term_end_date`) VALUES ('F22', 'FALL 2022', '2022-09-01', '2022-12-20');
INSERT INTO `CSCI5308_15_DEVINT`.`university_term` (`term_id`, `term_name`, `term_start_date`, `term_end_date`) VALUES ('S22', 'SUMMER 2022', '2022-05-01', '2022-07-31');
INSERT INTO `CSCI5308_15_DEVINT`.`university_term` (`term_id`, `term_name`, `term_start_date`, `term_end_date`) VALUES ('W23', 'WINTER 2023', '2023-01-09', '2023-04-30');
