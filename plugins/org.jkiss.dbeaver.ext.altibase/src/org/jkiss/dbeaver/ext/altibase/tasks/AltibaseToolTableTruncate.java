/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ext.altibase.tasks;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.ext.altibase.model.AltibaseTable;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.edit.SQLDatabasePersistAction;
import org.jkiss.dbeaver.model.sql.task.SQLToolExecuteHandler;

import java.util.List;

public class AltibaseToolTableTruncate extends SQLToolExecuteHandler<AltibaseTable, AltibaseToolTableTruncateSettings> {

    @NotNull
    @Override
    public AltibaseToolTableTruncateSettings createToolSettings() {
        return new AltibaseToolTableTruncateSettings();
    }

    @Override
    public void generateObjectQueries(
            DBCSession session,
            AltibaseToolTableTruncateSettings settings,
            List<DBEPersistAction> queries,
            AltibaseTable object) {

        StringBuilder sql = new StringBuilder("TRUNCATE TABLE ").append(object.getFullyQualifiedName(DBPEvaluationContext.DDL));
        queries.add(new SQLDatabasePersistAction(sql.toString()));
    }

    @Override
    public boolean needsRefreshOnFinish() {
        return true;
    }
}