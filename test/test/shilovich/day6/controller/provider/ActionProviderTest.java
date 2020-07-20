package test.shilovich.day6.controller.provider;

import com.shilovich.day6.controller.command.impl.FindByTagCommand;
import com.shilovich.day6.controller.provider.ActionProvider;
import com.shilovich.day6.exception.ControllerException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.fail;

public class ActionProviderTest {
    ActionProvider provider;

    @BeforeClass
    public void setUp() {
        provider = new ActionProvider();
    }

    @Test
    public void testDefineCommandPositive() throws ControllerException {
        String command = "find_by_tag_command";
        var actual = provider.defineCommand(command);
        var expected = new FindByTagCommand();
        assertEquals(actual.getClass(), expected.getClass());
    }

    @Test
    public void testDefineCommandNegative() throws ControllerException {
        String command = "sort_by_tag_command";
        var actual = provider.defineCommand(command);
        var expected = new FindByTagCommand();
        assertNotEquals(actual.getClass(), expected.getClass());
    }

    @Test(expectedExceptions = ControllerException.class)
    public void testDefineCommandException() throws ControllerException {
        String command = "sort_by_tag_comand";
        provider.defineCommand(command);
        fail("No exception was thrown!");
    }
}