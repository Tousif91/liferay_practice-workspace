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

package com.common.db.model.impl;

import com.common.db.model.News;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing News in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NewsCacheModel implements CacheModel<News>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsCacheModel)) {
			return false;
		}

		NewsCacheModel newsCacheModel = (NewsCacheModel)object;

		if (id == newsCacheModel.id) {
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
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", subTitle=");
		sb.append(subTitle);
		sb.append(", description=");
		sb.append(description);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public News toEntityModel() {
		NewsImpl newsImpl = new NewsImpl();

		if (uuid == null) {
			newsImpl.setUuid("");
		}
		else {
			newsImpl.setUuid(uuid);
		}

		newsImpl.setId(id);
		newsImpl.setUserId(userId);

		if (title == null) {
			newsImpl.setTitle("");
		}
		else {
			newsImpl.setTitle(title);
		}

		if (subTitle == null) {
			newsImpl.setSubTitle("");
		}
		else {
			newsImpl.setSubTitle(subTitle);
		}

		if (description == null) {
			newsImpl.setDescription("");
		}
		else {
			newsImpl.setDescription(description);
		}

		newsImpl.setStatus(status);

		newsImpl.resetOriginalValues();

		return newsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		id = objectInput.readLong();

		userId = objectInput.readLong();
		title = objectInput.readUTF();
		subTitle = objectInput.readUTF();
		description = objectInput.readUTF();

		status = objectInput.readInt();
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

		objectOutput.writeLong(userId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (subTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subTitle);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long id;
	public long userId;
	public String title;
	public String subTitle;
	public String description;
	public int status;

}