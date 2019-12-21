package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.base.MapUtil;
import com.util.base.ModelUtil;

class ModelUtilTest {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> roles = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> role = new HashMap<>();
			role.put("id", "test"+i);
			role.put("name", "姓名" + i);
			roles.add(role);
		}
		map.put("id", "zfd");
		map.put("name", "赵丰登");
		map.put("roles", roles);
		User user = ModelUtil.toModel(map, User.class);
		System.out.println(user.getId());
		System.out.println(user.getName());
		List<Role> roles2 = user.getRoles();
		for (Role role : roles2) {
			System.out.println(role.getId()+"=========="+role.getName());
		}
	}
}
