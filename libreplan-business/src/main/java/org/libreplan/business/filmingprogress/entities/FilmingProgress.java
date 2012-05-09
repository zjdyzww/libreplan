/*
 * This file is part of LibrePlan
 *
 * Copyright (C) 2012 WirelessGalicia, S.L.
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
package org.libreplan.business.filmingprogress.entities;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import org.libreplan.business.common.BaseEntity;

/**
 * Represents the progress associated to a filming.
 *
 * @author Susana Montes Pedreira <smontes@wirelessgalicia.com>
 */
public class FilmingProgress extends BaseEntity {

    private LocalDate startDate;

    private LocalDate endDate;

    private ProgressGranularityType progressGranularity;

    private Map<LocalDate, Integer> initialProgressForecast = new HashMap<LocalDate, Integer>();

    private Map<LocalDate, Integer> progressForecast = new HashMap<LocalDate, Integer>();

    private Map<LocalDate, Integer> realProgress = new HashMap<LocalDate, Integer>();

    protected FilmingProgress() {

    }

    private FilmingProgress(LocalDate startDate, LocalDate endDate,
            ProgressGranularityType progressGranularity) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setProgressGranularity(progressGranularity);
    }

    public static FilmingProgress create() {
        return create(new FilmingProgress());
    }

    public static FilmingProgress create(LocalDate startDate, LocalDate endDate,
            ProgressGranularityType progressGranularity) {
        return create(new FilmingProgress(startDate, endDate, progressGranularity));
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setProgressGranularity(ProgressGranularityType progressGranularity) {
        this.progressGranularity = progressGranularity;
    }

    public ProgressGranularityType getProgressGranularity() {
        return progressGranularity;
    }

    public void setInitialProgressForecast(Map<LocalDate, Integer> initialProgressForecast) {
        this.initialProgressForecast = initialProgressForecast;
    }

    public Map<LocalDate, Integer> getInitialProgressForecast() {
        return initialProgressForecast;
    }

    public void setProgressForecast(Map<LocalDate, Integer> progressForecast) {
        this.progressForecast = progressForecast;
    }

    public Map<LocalDate, Integer> getProgressForecast() {
        return progressForecast;
    }

    public void setRealProgress(Map<LocalDate, Integer> realProgress) {
        this.realProgress = realProgress;
    }

    public Map<LocalDate, Integer> getRealProgress() {
        return realProgress;
    }
}
