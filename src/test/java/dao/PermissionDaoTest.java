package dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import model.Permission;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import service.PermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class PermissionDaoTest {
    @Autowired
    private PermissionService permissionService;

    @Test
    @DatabaseSetup("classpath:table/dbData.xml")
    public void shouldFindPermission() throws Exception {
        //when
        Permission permission = permissionService.findByName("user");

        //then
        Assert.assertNotNull(permission);
        Assert.assertEquals("user", permission.getName());
    }

    @Test
    @DatabaseSetup("classpath:table/dbData.xml")
    public void shouldBeNull() throws Exception {
        //when
        Permission permission = permissionService.findByName("superMegaAdmin");

        //then
        Assert.assertNull(permission);
    }

    @Test
    @DatabaseSetup("classpath:table/dbData.xml")
    @ExpectedDatabase("classpath:table/updatePermissionData.xml")
    public void shouldUpdatePermission() throws Exception {
        //given
        Permission permission = new Permission();
        permission.setId(2L);
        permission.setName("moderator");

        //when
        permissionService.update(permission);
    }

    @Test
    @DatabaseSetup("classpath:table/dbData.xml")
    @ExpectedDatabase("classpath:table/removePermissionData.xml")
    public void shouldRemovePermission() throws Exception {
        //given
        Permission permission = new Permission();
        permission.setName("user");
        permission.setId(2L);

        //when
        permissionService.remove(permission);
    }

    @Test(expected = Exception.class)
    @DatabaseSetup("classpath:table/dbData.xml")
    public void shouldFailedRemoveIfNotExistPermission() throws Exception {
        //given
        Permission permission = new Permission();
        permission.setName("user");
        permission.setId(6L);

        //when
        permissionService.remove(permission);
        Assert.fail("can't remove permission if not exist");
    }

    @Test
    @DatabaseSetup("classpath:table/dbData.xml")
    @ExpectedDatabase("classpath:table/createPermissionData.xml")
    public void shouldCreatePermission() throws Exception {
        //given
        Permission permission = new Permission();
        permission.setName("moderator");

        //when
        permissionService.create(permission);
    }
    @Test(expected = Exception.class)
    @DatabaseSetup("classpath:table/dbData.xml")
    public void shouldBeThrowExceptionIfPermissionExist() throws Exception {
        //given
        Permission permission = new Permission();

        //when
        permissionService.create(permission);
        Assert.fail("You can not create two identical permission");
    }

    @Test(expected = Exception.class)
    @DatabaseSetup("classpath:table/dbData.xml")
    @ExpectedDatabase("classpath:table/dbData.xml")
    public void shouldNotCreateNullPermission() throws Exception {
        //when
        permissionService.create(null);
        Assert.fail("item should be tested for null");
    }

    @Test(expected = Exception.class)
    @DatabaseSetup("classpath:table/dbData.xml")
    @ExpectedDatabase("classpath:table/dbData.xml")
    public void shouldNotRemoveNullPermission() throws Exception {
        //when
        permissionService.remove(null);
        Assert.fail("item should be tested for null");
    }

    @Test(expected = Exception.class)
    @DatabaseSetup("classpath:table/dbData.xml")
    @ExpectedDatabase("classpath:table/dbData.xml")
    public void shouldNotUpdateNullPermission() throws Exception {
        //when
        permissionService.update(null);
        Assert.fail("item should be tested for null");
    }

}
