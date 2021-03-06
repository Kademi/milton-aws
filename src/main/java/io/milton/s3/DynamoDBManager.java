/*
 * Copyright (C) McEvoy Software Ltd
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.milton.s3;

import io.milton.s3.model.Entity;
import io.milton.s3.model.Folder;

import java.util.List;

public interface DynamoDBManager {

    /**
     * Create storage database in Amazon DynamoDB for the given table name
     * 
     * @param tableName
     *              - the storage database name
     */
	boolean createTable(String tableName);
    
    /**
     * Delete storage database in Amazon DynamoDB for the given table name
     * 
     * @param tableName
     *              - the storage database name
     */
	boolean deleteTable(String tableName);
    
    boolean isExistEntity(String tableName, String entityName, Folder parent);
    
    boolean putEntity(String tableName, Entity entity);
    
    Folder findRootFolder(String tableName);
    
    Entity findEntityByUniqueId(String tableName, Entity entity);
    
    Entity findEntityByUniqueId(String tableName, String uniqueId, Folder parent);
    
    List<Entity> findEntityByParent(String tableName, Folder parent);
    
    List<Entity> findEntityByParentAndType(String tableName, Folder parent, boolean isDirectory);
    
    boolean updateEntityByUniqueId(String tableName, Entity entity, Folder newParent, 
            String newEntityName, boolean isRenamingAction);
    
    boolean deleteEntityByUniqueId(String tableName, String uniqueId);
}
