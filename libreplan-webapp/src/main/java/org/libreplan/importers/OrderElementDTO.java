/*
 * This file is part of LibrePlan
 *
 * Copyright (C) 2012 Igalia, S.L.
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

package org.libreplan.importers;

import java.util.Date;
import java.util.List;

import org.libreplan.business.orders.entities.OrderElement;

/**
 * Class that represents no persistent imported tasks. <br />
 *
 * At these moment it only represent the tasks that can have any subtasks.
 *
 * @author Alba Carro Pérez <alba.carro@gmail.com>
 * @todo It last hours, resources, relationships, etc.
 */
public class OrderElementDTO {

    /**
     * Name of the task
     */
    public String name;

    /**
     * Start date of the task
     */
    public Date startDate;

    /**
     * end date of the task
     */
    public Date endDate;

    /**
     * Order created with this data
     */
    public OrderElement orderElement;

    /**
     * List of task that are children of this task
     */
    public List<OrderElementDTO> children;

}