/*******************************************************************************
 * Copyright 2012 Sevket Seref Arikan, David Ingram
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package uk.ac.ucl.chime.db;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "archetype_data", schema = "app")
public class ArchetypeData implements java.io.Serializable {

	// Fields

	private Long id;
	private String contextId;
	private String archetypeName;
	private String archetypePath;
	private String name;
	private String valueString;
	private Long valueInt;
	private Double valueDouble;
	private String sessionId;
	private Integer instanceIndex;
	private Timestamp fieldCreatedAt;
	private Timestamp archetypeCreatedAt;
	private String tolvenContext;
	private String valueAtPath;
	private Boolean deleted;

	// Constructors

	/** default constructor */
	public ArchetypeData() {
	}

	/** full constructor */
	public ArchetypeData(String contextId, String archetypeName,
			String archetypePath, String name, String valueString,
			Long valueInt, Double valueDouble, String sessionId,
			Integer instanceIndex, Timestamp fieldCreatedAt,
			Timestamp archetypeCreatedAt, String tolvenContext,
			String valueAtPath, Boolean deleted) {
		this.contextId = contextId;
		this.archetypeName = archetypeName;
		this.archetypePath = archetypePath;
		this.name = name;
		this.valueString = valueString;
		this.valueInt = valueInt;
		this.valueDouble = valueDouble;
		this.sessionId = sessionId;
		this.instanceIndex = instanceIndex;
		this.fieldCreatedAt = fieldCreatedAt;
		this.archetypeCreatedAt = archetypeCreatedAt;
		this.tolvenContext = tolvenContext;
		this.valueAtPath = valueAtPath;
		this.deleted = deleted;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "context_id", length = 1000)
	public String getContextId() {
		return this.contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	@Column(name = "archetype_name", length = 1000)
	public String getArchetypeName() {
		return this.archetypeName;
	}

	public void setArchetypeName(String archetypeName) {
		this.archetypeName = archetypeName;
	}

	@Column(name = "archetype_path", length = 1000)
	public String getArchetypePath() {
		return this.archetypePath;
	}

	public void setArchetypePath(String archetypePath) {
		this.archetypePath = archetypePath;
	}

	@Column(name = "name", length = 1000)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value_string", length = 1000)
	public String getValueString() {
		return this.valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	@Column(name = "value_int")
	public Long getValueInt() {
		return this.valueInt;
	}

	public void setValueInt(Long valueInt) {
		this.valueInt = valueInt;
	}

	@Column(name = "value_double", precision = 17, scale = 17)
	public Double getValueDouble() {
		return this.valueDouble;
	}

	public void setValueDouble(Double valueDouble) {
		this.valueDouble = valueDouble;
	}

	@Column(name = "session_id", length = 40)
	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "instance_index")
	public Integer getInstanceIndex() {
		return this.instanceIndex;
	}

	public void setInstanceIndex(Integer instanceIndex) {
		this.instanceIndex = instanceIndex;
	}

	
	@Column(name = "field_created_at", length = 22)
	public Timestamp getFieldCreatedAt() {
		return this.fieldCreatedAt;
	}

	public void setFieldCreatedAt(Timestamp fieldCreatedAt) {
		this.fieldCreatedAt = fieldCreatedAt;
	}

	
	@Column(name = "archetype_created_at", length = 22)
	public Timestamp getArchetypeCreatedAt() {
		return this.archetypeCreatedAt;
	}

	public void setArchetypeCreatedAt(Timestamp archetypeCreatedAt) {
		this.archetypeCreatedAt = archetypeCreatedAt;
	}

	@Column(name = "tolven_context", length = 1000)
	public String getTolvenContext() {
		return this.tolvenContext;
	}

	public void setTolvenContext(String tolvenContext) {
		this.tolvenContext = tolvenContext;
	}

	@Column(name = "value_at_path")
	public String getValueAtPath() {
		return this.valueAtPath;
	}

	public void setValueAtPath(String valueAtPath) {
		this.valueAtPath = valueAtPath;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}

