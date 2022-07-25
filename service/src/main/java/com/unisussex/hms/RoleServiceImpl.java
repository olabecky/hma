package com.unisussex.hms;

import com.unisussex.hms.hibernate.RoleDao;
import com.unisussex.hms.hibernate.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;
	private final EntityRoleConverter entityRoleConverter;

	public RoleServiceImpl(RoleDao roleDao,
						   EntityRoleConverter entityRoleConverter) {
		this.roleDao = roleDao;
		this.entityRoleConverter = entityRoleConverter;
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.findAll()
				.stream()
				.map(entityRoleConverter::convert)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return roleDao.findByName(name)
				.map(entityRoleConverter::convert);
	}

	@Override
	public List<Role> getRoleByParam(String param) {
		return roleDao.findByParam(param)
				.stream()
				.map(entityRoleConverter::convert)
				.collect(Collectors.toList());
	}

	@Override
	public Role saveRole(Role role) {
		if(roleDao.findByName(role.getName()).isPresent()) {
			throw new IllegalArgumentException("Role already exists with name: " + role.getName());
		} else {
			RoleEntity savedRole = roleDao.save(entityRoleConverter.convert(role));

			return entityRoleConverter.convert(savedRole);
		}
	}

	@Override
	public Role updateRole(Role role) {
		Optional<RoleEntity> roleEntity = roleDao.findById(role.getId());

		if(roleEntity.isEmpty()) {
			throw new IllegalArgumentException("No Role exists with id: " + role.getId());
		} else {
			RoleEntity entityInDB = roleEntity.get();
			entityInDB.setName(role.getName());
			entityInDB.setDescription(role.getDescription());

			entityInDB = roleDao.save(entityInDB);

			return entityRoleConverter.convert(entityInDB);
		}
	}

	@Override
	public void saveRoless(List<Role> roles) {
		List<RoleEntity> roleEntities = roles.stream()
				.map(entityRoleConverter::convert)
				.collect(Collectors.toList());

		roleDao.saveAll(roleEntities);
	}

	@Override
	public void delete(long id) {
		roleDao.deleteById(id);
	}

	@Override
	public void deleteAll() {
		roleDao.deleteAll();
	}
}
