/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.student.db.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.student.db.model.Teacher;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Teacher in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeacherCacheModel implements CacheModel<Teacher>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TeacherCacheModel)) {
			return false;
		}

		TeacherCacheModel teacherCacheModel = (TeacherCacheModel)object;

		if (id == teacherCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", name=");
		sb.append(name);
		sb.append(", address=");
		sb.append(address);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Teacher toEntityModel() {
		TeacherImpl teacherImpl = new TeacherImpl();

		if (uuid == null) {
			teacherImpl.setUuid("");
		}
		else {
			teacherImpl.setUuid(uuid);
		}

		teacherImpl.setId(id);

		if (name == null) {
			teacherImpl.setName("");
		}
		else {
			teacherImpl.setName(name);
		}

		if (address == null) {
			teacherImpl.setAddress("");
		}
		else {
			teacherImpl.setAddress(address);
		}

		teacherImpl.resetOriginalValues();

		return teacherImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();
		name = objectInput.readUTF();
		address = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(id);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}
	}

	public String uuid;
	public long id;
	public String name;
	public String address;

}