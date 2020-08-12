create table `gaokao` (
                          `code` int (11),
                          `scid` int (11),
                          `sid` int (11),
                          `average` int (11),
                          `year` int (4),
                          `min` int (11),
                          `school_id` int (11),
                          `min_section` int (255),
                          `max` int (11),
                          `local_province_name` varchar (30),
                          `local_batch_name` varchar (93),
                          `spname` varchar (300),
                          `name` varchar (60),
                          `local_type_name` int (10),
                          `rank` int (11),
                          `proscore` varchar (765),
                          `areaname` varchar (765),
                          `is985` int (11),
                          `is211` int (11),
                          `isDoubleFirstClass` int (11),
                          `type` varchar(20)

);