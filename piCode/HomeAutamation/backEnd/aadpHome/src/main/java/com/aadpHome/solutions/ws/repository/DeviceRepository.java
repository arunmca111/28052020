package com.aadpHome.solutions.ws.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadpHome.solutions.ws.io.entity.Device;
import com.aadpHome.solutions.ws.shared.dto.DeviceDto;

@Repository
@Transactional
public class DeviceRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Device findDeviceById(String deviceId) {
		Device device = em.find(Device.class, deviceId);
		logger.info("Device -> {}", device);
		return device;
	}

	public List<Object[]> findDeviceBymachineId(String machineId) {

		logger.info("MachineId -> {}", machineId);
		
		/*
		 * TypedQuery<DeviceDto> query = em.createQuery(
		 * "select m.machine_slot_id, p.* from machine_inventory m JOIN Device p ON "
		 * + "m.product_id =p.product_id WHERE " + "m.machine_code = :machineId and  " +
		 * "m.machine_slot_active_status = true and  " + "m.machine_item_count > 0  " +
		 * "order by m.machine_slot_id", DeviceDto.class); return
		 * query.setParameter("machineId", machineId).getResultList();
		 */
		
		List<Object[]> results = em.createQuery(
				"select m.machineSlotId,p.productId,p.title,p.description,p.price,p.imageUrl,p.isFavorite from MachineInventory m JOIN Device p ON "
						+ "m.productId =p.productId "
				).getResultList();
		
		return results;

	}

	public List<Device> findAllDevice() {
		return em.createQuery("select d from Device d", Device.class).getResultList();
	}

	public List<Device> getDeviceByuserID(String userId) {
		TypedQuery<Device> query = em.createQuery("SELECT d FROM Device d WHERE d.userId = :userid",
				Device.class);
		return query.setParameter("userid", userId).getResultList();
	}

	public Device addDevice(Device Device) {

		logger.info("adding Device");

		em.persist(Device);
		em.flush();

		logger.info("Device Added -> {}", Device.getTitle());

		return Device;

	}

	public Device updateDevice(Device Device) {

		logger.info("adding Device");

		em.merge(Device);
		em.flush();

		logger.info("Device Updated -> {}", Device.getTitle());

		return Device;

	}

	public void deleteDevice(Device Device) {

		logger.info("deleting Device");

		em.remove(Device);
		em.flush();

		logger.info("Device Deleted -> {}", Device.getTitle());

	}

}