CREATE TABLE `user` (
                        `id` int(32) NOT NULL AUTO_INCREMENT,
                        `userName` varchar(32) NOT NULL,
                        `passWord` varchar(50) NOT NULL,
                        `userRealname` varchar(20) DEFAULT NULL,
                        `usergender` varchar(10) DEFAULT NULL,
                        `userphone` varchar(20) NOT NULL,
                        `userwechat` varchar(20) DEFAULT NULL,
                        `userscore` int(11) DEFAULT NULL,
                        `userarea` varchar(20) DEFAULT NULL,
                        `usersort` varchar(10) DEFAULT NULL,
                        `userrank` int(11) DEFAULT NULL,
                        `admin` int(100) DEFAULT NULL,
                        `token` varchar(255) DEFAULT NULL,
                        `vip` int(10) DEFAULT NULL,
                        PRIMARY KEY (`id`)
)