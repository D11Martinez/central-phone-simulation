package central.telephone.simulation;

import central.telephone.simulation.entities.CentralTelephone;
import central.telephone.simulation.entities.TelephoneLine;
import central.telephone.simulation.entities.UserEntity;
import central.telephone.simulation.services.CentralTelephoneService;
import central.telephone.simulation.services.RoleService;
import central.telephone.simulation.services.TelephoneLineService;
import central.telephone.simulation.services.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimulationApplicationTests {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;


	@Autowired
	@Qualifier("centralTelephoneService")
	private CentralTelephoneService centralTelephoneService;

	@Autowired
	@Qualifier("telephoneLineService")
	private TelephoneLineService telephoneLineService;

	@Test
	void contextLoads() throws NotFoundException {
		// Roles
		roleService.createRoleIfNotExists("ADMIN","Central Telephone Administrator");
    roleService.createRoleIfNotExists("CLIENT","Central Telephone Client");

		// User with ADMIN role
    UserEntity admin = userService.createUserIfNotExists("admin","1234","ADMIN");

		// Users with CLIENT role
    UserEntity client01 = userService.createUserIfNotExists("client01","1234","CLIENT");
    UserEntity client02 = userService.createUserIfNotExists("client02","1234","CLIENT");
    UserEntity client03 = userService.createUserIfNotExists("client03","1234","CLIENT");
		UserEntity client04 = userService.createUserIfNotExists("client04","1234","CLIENT");
		UserEntity client05 = userService.createUserIfNotExists("client05","1234","CLIENT");
		UserEntity client06 = userService.createUserIfNotExists("client06","1234","CLIENT");
		UserEntity client07 = userService.createUserIfNotExists("client07","1234","CLIENT");
		UserEntity client08 = userService.createUserIfNotExists("client08","1234","CLIENT");
		UserEntity client09 = userService.createUserIfNotExists("client09","1234","CLIENT");
		UserEntity client10 = userService.createUserIfNotExists("client10","1234","CLIENT");
		UserEntity client11 = userService.createUserIfNotExists("client11","1234","CLIENT");
		UserEntity client12 = userService.createUserIfNotExists("client12","1234","CLIENT");
		UserEntity client13 = userService.createUserIfNotExists("client13","1234","CLIENT");
		UserEntity client14 = userService.createUserIfNotExists("client14","1234","CLIENT");
		UserEntity client15 = userService.createUserIfNotExists("client15","1234","CLIENT");

		// Central Telephone
    CentralTelephone centralTelephone1 = centralTelephoneService.createCentralTelephoneIfNotExists("Central Telephones 1",30);
		CentralTelephone centralTelephone2 = centralTelephoneService.createCentralTelephoneIfNotExists("Central Telephones 2",10);

		// Telephone lines centralTelephone1
		telephoneLineService.createTelephoneLineIfNotExists(7001L, true,client01, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7002L, true,client02, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7003L, true,client03, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7004L, true,client04, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7005L, true,client05, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7006L, true,client06, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7007L, true,client07, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7008L, true,client08, centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7009L, false, null,centralTelephone1);
		telephoneLineService.createTelephoneLineIfNotExists(7010L, false, admin, centralTelephone1);

		// Telephone lines centralTelephone2
		telephoneLineService.createTelephoneLineIfNotExists(7011L, true, client09, centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7012L, true, client10, centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7013L, true, client11, centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7014L, true, client12, centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7015L, true, client13, centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7016L, true, client14, centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7017L, true, client15, centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7018L, false, null,centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7019L, false, null,centralTelephone2);
		telephoneLineService.createTelephoneLineIfNotExists(7020L, false, null, centralTelephone2);

	}

}
