package com.unisussex.hms;

import java.util.List;
import java.util.Optional;

public interface RoleService {

	List<Role> getAllRoles();

	Optional<Role> getRoleByName(String name);

	List<Role> getRoleByParam(String param);

	Role saveRole(Role role);

	Role updateRole(Role role);

	void saveRoless(List<Role> roles);

	void delete(long id);

	void deleteAll();

}
