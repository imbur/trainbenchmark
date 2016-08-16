SELECT DISTINCT
    Route1.exit AS semaphore,
    Route1.id AS route1,
    Route2.id AS route2,
    Sensor1.id AS sensor1,
    Sensor2.id AS sensor2,
    ct.TrackElement1_id AS te1,
    ct.TrackElement2_id AS te2

-- (route1)
FROM Route AS Route1

-- (route1)-[:gathers]->(sensor1)
INNER JOIN Sensor AS Sensor1
ON Route1.id = Sensor1.route

-- (sensor1)<-[:monitoredBy]-(te1)
INNER JOIN monitoredBy AS mb1
ON Sensor1.id = mb1.Sensor_id

-- (te1)-[:connectsTo]->(te2)
INNER JOIN connectsTo AS ct
ON mb1.TrackElement_id = ct.TrackElement1_id

-- (te2)-[:monitoredBy]->(sensor2)
INNER JOIN monitoredBy AS mb2
ON ct.TrackElement2_id = mb2.TrackElement_id

-- (sensor2)
INNER JOIN Sensor AS Sensor2
ON mb2.Sensor_id = Sensor2.id

-- (sensor2)<-[:gathers]-(route2)
INNER JOIN Route AS Route2
ON Sensor2.route = Route2.id

WHERE Route1.id != Route2.id
  AND Route1.exit IS NOT NULL -- semaphore
  AND (Route2.entry IS NULL OR Route2.entry != Route1.exit);
