/*
 * This file is part of ###PROJECT_NAME###
 *
 * Copyright (C) 2009 Fundación para o Fomento da Calidade Industrial e
 *                    Desenvolvemento Tecnolóxico de Galicia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.navalplanner.business.test.users.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.navalplanner.business.BusinessGlobalNames.BUSINESS_SPRING_CONFIG_FILE;
import static org.navalplanner.business.test.BusinessGlobalNames.BUSINESS_SPRING_CONFIG_TEST_FILE;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.navalplanner.business.common.IAdHocTransactionService;
import org.navalplanner.business.common.IOnTransaction;
import org.navalplanner.business.common.exceptions.InstanceNotFoundException;
import org.navalplanner.business.common.exceptions.ValidationException;
import org.navalplanner.business.users.daos.IUserDAO;
import org.navalplanner.business.users.entities.User;
import org.navalplanner.business.users.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tests for <code>IUserDAO</code>.
 *
 * @author Fernando Bellas Permuy <fbellas@udc.es>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { BUSINESS_SPRING_CONFIG_FILE,
    BUSINESS_SPRING_CONFIG_TEST_FILE })
@Transactional
public class UserDAOTest {

    @Autowired
    private IAdHocTransactionService transactionService;

    @Autowired
    private IUserDAO userDAO;

    @Test
    public void testBasicSave() throws InstanceNotFoundException {

        User user = createUser(getUniqueName());

        userDAO.save(user);

        User user2 = userDAO.find(user.getId());
        assertEquals(user, user2);

    }

    @Test
    @NotTransactional
    public void testExistsByLoginNameAnotherTransaction() {

        final String loginName = getUniqueName();

        IOnTransaction<Void> createUser = new IOnTransaction<Void>() {
            @Override
            public Void execute() {
                userDAO.save(createUser(loginName));
                return null;
            }
        };

        transactionService.runOnTransaction(createUser);

        assertTrue(userDAO.existsByLoginNameAnotherTransaction(loginName));
        assertTrue(userDAO.existsByLoginNameAnotherTransaction(
            loginName.toUpperCase()));
        assertTrue(userDAO.existsByLoginNameAnotherTransaction(
            loginName.toLowerCase()));

    }

    @Test
    @NotTransactional
    public void testSaveWithExistingLoginName() {

        final String loginName = getUniqueName();

        IOnTransaction<Void> createUser = new IOnTransaction<Void>() {
            @Override
            public Void execute() {
                userDAO.save(createUser(loginName));
                return null;
            }
        };

        transactionService.runOnTransaction(createUser);

        try {
            transactionService.runOnTransaction(createUser);
            fail("ValidationException expected");
        } catch (ValidationException e) {
        }

    }

    private String getUniqueName() {
        return UUID.randomUUID().toString();
    }

    private User createUser(String loginName) {

        Set<UserRole> roles = new HashSet<UserRole>();
        roles.add(UserRole.ROLE_BASIC_USER);
        roles.add(UserRole.ROLE_ADMINISTRATION);

        return User.create(loginName, loginName, roles);

    }

}
