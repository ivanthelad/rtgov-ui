/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.monitoring.ui.client.local.pages.services;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.TransitionAnchorFactory;
import org.overlord.monitoring.ui.client.local.ClientMessages;
import org.overlord.monitoring.ui.client.local.pages.ComponentServiceDetailsPage;
import org.overlord.monitoring.ui.client.local.widgets.common.SortableTemplatedWidgetTable;
import org.overlord.monitoring.ui.client.shared.beans.ComponentServiceSummaryBean;
import org.overlord.monitoring.ui.client.shared.beans.Constants;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.InlineLabel;

/**
 * A table of component services.
 *
 * @author eric.wittmann@redhat.com
 */
@Dependent
public class ComponentServiceTable extends SortableTemplatedWidgetTable {

    @Inject
    protected ClientMessages i18n;
    @Inject
    protected TransitionAnchorFactory<ComponentServiceDetailsPage> toDetailsPageLinkFactory;

    /**
     * Constructor.
     */
    public ComponentServiceTable() {
    }

    /**
     * @see org.overlord.monitoring.ui.client.local.widgets.common.SortableTemplatedWidgetTable#getDefaultSortColumn()
     */
    @Override
    protected SortColumn getDefaultSortColumn() {
        SortColumn sortColumn = new SortColumn();
        sortColumn.columnId = Constants.SORT_COLID_NAME;
        sortColumn.ascending = true;
        return sortColumn;
    }

    /**
     * @see org.overlord.monitoring.ui.client.local.widgets.common.SortableTemplatedWidgetTable#configureColumnSorting()
     */
    @Override
    protected void configureColumnSorting() {
        setColumnSortable(0, Constants.SORT_COLID_NAME);
        setColumnSortable(4, Constants.SORT_COLID_AVERAGE_DURATION);
        sortBy(Constants.SORT_COLID_NAME, true);
    }

    /**
     * Adds a single row to the table.
     * @param summaryBean
     */
    public void addRow(final ComponentServiceSummaryBean summaryBean) {
        int rowIdx = this.rowElements.size();

        Anchor name = toDetailsPageLinkFactory.get("id", summaryBean.getServiceId()); //$NON-NLS-1$
        name.setText(summaryBean.getName());
        InlineLabel application = new InlineLabel(summaryBean.getApplication());
        InlineLabel interf4ce = new InlineLabel(summaryBean.getIface());
        InlineLabel implementation = new InlineLabel(summaryBean.getImplementation());
        InlineLabel averageDuration = new InlineLabel(formatDuration(summaryBean.getAverageDuration()));

        add(rowIdx, 0, name);
        add(rowIdx, 1, application);
        add(rowIdx, 2, interf4ce);
        add(rowIdx, 3, implementation);
        add(rowIdx, 4, averageDuration);
    }

    /**
     * Formats an average duration (in milliseconds) into a human readable format.
     * @param averageDuration
     */
    private String formatDuration(long averageDuration) {
        // TODO implement this!
        return String.valueOf(averageDuration);
    }

}
