CREATE DATABASE `final_project_servlet`;
CREATE TABLE `final_project_servlet`.`roles` (
                                                  `id` BIGINT(20) NOT NULL,
                                                  `role` VARCHAR(255) NULL,
                                                  PRIMARY KEY (`id`));
CREATE TABLE `final_project_servlet`.`statuses` (
                                                     `id` INT NOT NULL,
                                                     `statuses` VARCHAR(255) NULL,
                                                     PRIMARY KEY (`id`));
CREATE TABLE `final_project_servlet`.`usr` (
                                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                `active` BIT(1) NOT NULL,
                                                `password` VARCHAR(255) NULL DEFAULT NULL,
                                                `username` VARCHAR(255) NULL DEFAULT NULL,
                                                `role_id` BIGINT(20) NOT NULL,
                                                PRIMARY KEY (`id`));
CREATE TABLE `final_project_servlet`.`repair_request` (
                                                           `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                           `active` BIT(1) NOT NULL,
                                                           `description` VARCHAR(255) NULL DEFAULT NULL,
                                                           `feedback` VARCHAR(255) NULL DEFAULT NULL,
                                                           `price` INT(11) NULL DEFAULT NULL,
                                                           `theme` VARCHAR(255) NULL,
                                                           `user_id` BIGINT(20) NOT NULL,
                                                           `status_id` BIGINT(20) NOT NULL,
                                                           PRIMARY KEY (`id`));
ALTER TABLE `final_project_servlet`.`usr` ADD FOREIGN KEY (role_id) REFERENCES roles(id);
ALTER TABLE `final_project_servlet`.`repair_request` ADD FOREIGN KEY (user_id) REFERENCES usr(id);
ALTER TABLE `final_project_servlet`.`repair_request` ADD FOREIGN KEY (status_id) REFERENCES statuses(id);

INSERT INTO `final_project_servlet`.`roles` (`id`, `role`) VALUES ('0', 'ROLE_USER');
INSERT INTO `final_project_servlet`.`roles` (`id`, `role`) VALUES ('1', 'ROLE_MANAGER');
INSERT INTO `final_project_servlet`.`roles` (`id`, `role`) VALUES ('2', 'ROLE_MASTER');

INSERT INTO `final_project_servlet`.`statuses` (`id`, `status`) VALUES ('0', 'CURRENT_REQUEST');
INSERT INTO `final_project_servlet`.`statuses` (`id`, `status`) VALUES ('1', 'ACCEPTED_REQUEST');
INSERT INTO `final_project_servlet`.`statuses` (`id`, `status`) VALUES ('2', 'CLOSED_REQUEST');
INSERT INTO `final_project_servlet`.`statuses` (`id`, `status`) VALUES ('3', 'DECLINED_REQUEST');