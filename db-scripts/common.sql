select * FROM weatherstation.temperature_reading order by read_date desc limit 0,2000;

delete from temperature_reading where read_date < DATE_SUB(NOW(), INTERVAL 60 DAY)  
and hour(read_date) not in (0,3,6,9,12,15,18,21) and minute(read_date)>=15;

