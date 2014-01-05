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
package io.milton.s3.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Folder extends BaseEntity {
    
    private List<BaseEntity> childrens = new ArrayList<BaseEntity>();
    
    public Folder(String name, Folder parent) {
        super(name, parent);
    }
    
    public File addFile(final String name) {
        File file = new File(name, this);
        childrens.add(file);
        return file;
    }
    
    public Folder addFolder(final String name) {
        Folder folder = new Folder(name, this);
        childrens.add(folder);
        return folder;
    }

    /**
     * Remove entity for the given name in a folder
     * 
     * @param the given name of entity
     */
    public void remove(final String name) {
        Iterator<BaseEntity> iterator = childrens.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getName().equals(name)) {
                iterator.remove();
            }
        }
    }
    
    /**
     * Get all entities have existing in a folder
     * 
     * @return a list of entities
     */
    public List<BaseEntity> getChildren() {
        return childrens;
    }
    
    /**
     * Get a entity for the given name in a folder
     * 
     * @param name
     * @return
     */
    public BaseEntity child(final String name) {
        for(BaseEntity entity : childrens) {
            if(entity.getName().equals(name))
                return entity;
        }
        return null;
    }
    
    @Override
    public void moveTo(final Folder target) {
        super.moveTo(target);
    }
    
    @Override
    public Folder copyTo(final Folder target, final String targetName) {
        Folder newFolder = target.addFolder(targetName);
        for(BaseEntity entity : childrens) {
            entity.copyTo(newFolder, entity.getName());
        }
        return newFolder;
    }
}