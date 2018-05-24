SELECT DISTINCT(box.expressno) FROM xiakee_yzorderinfo item INNER JOIN xiakee_boxno box ON item.id = box.infoId 
WHERE item.orderid = 150716104391982

SELECT * FROM xiakee_yzorderinfo WHERE id = 1704

SELECT * FROM xiakee_boxno WHERE expressno = '9361289878603316927325'

SELECT b.infoId,b.modified,b.declared,b.expressno,b.price,b.sum,o.title,o.sku_properties_name pro,u.`name` FROM xiakee_boxno b LEFT JOIN xiakee_yzorderinfo o ON b.infoId = o.id JOIN xiakee_yzorders u ON o.orderid = u.orderid WHERE b.expressno IS NOT NULL AND b.declared in (0,2) ORDER BY b.modified DESC

SELECT b.infoId,b.modified,b.declared,b.expressno,b.price,b.sum,o.title,o.sku_properties_name,u.`name` FROM xiakee_boxno b LEFT JOIN xiakee_yzorderinfo o ON b.infoId = o.id JOIN xiakee_yzorders u ON o.orderid = u.orderid WHERE b.expressno IS NOT NULL AND b.declared in (0,2) ORDER BY b.modified DESC