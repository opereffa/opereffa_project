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
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;



public class ArchetypeDataDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ArchetypeDataDAO.class);
	// property constants
	public static final String CONTEXT_ID = "contextId";
	public static final String ARCHETYPE_NAME = "archetypeName";
	public static final String ARCHETYPE_PATH = "archetypePath";
	public static final String NAME = "name";
	public static final String VALUE_STRING = "valueString";
	public static final String VALUE_INT = "valueInt";
	public static final String VALUE_DOUBLE = "valueDouble";
	public static final String SESSION_ID = "sessionId";
	public static final String INSTANCE_INDEX = "instanceIndex";
	public static final String TOLVEN_CONTEXT = "tolvenContext";
	public static final String VALUE_AT_PATH = "valueAtPath";
	public static final String DELETED = "deleted";

	public void save(ArchetypeData transientInstance) {
		log.debug("saving ArchetypeData instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ArchetypeData persistentInstance) {
		log.debug("deleting ArchetypeData instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ArchetypeData findById(java.lang.Long id) {
		log.debug("getting ArchetypeData instance with id: " + id);
		try {
			ArchetypeData instance = (ArchetypeData) getSession().get(
					"uk.ac.ucl.chime.db.ArchetypeData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ArchetypeData> findByExample(ArchetypeData instance) {
		log.debug("finding ArchetypeData instance by example");
		try {
			List<ArchetypeData> results = (List<ArchetypeData>) getSession()
					.createCriteria("uk.ac.ucl.chime.db.ArchetypeData").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ArchetypeData instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ArchetypeData as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ArchetypeData> findByContextId(Object contextId) {
		return findByProperty(CONTEXT_ID, contextId);
	}

	public List<ArchetypeData> findByArchetypeName(Object archetypeName) {
		return findByProperty(ARCHETYPE_NAME, archetypeName);
	}

	public List<ArchetypeData> findByArchetypePath(Object archetypePath) {
		return findByProperty(ARCHETYPE_PATH, archetypePath);
	}

	public List<ArchetypeData> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ArchetypeData> findByValueString(Object valueString) {
		return findByProperty(VALUE_STRING, valueString);
	}

	public List<ArchetypeData> findByValueInt(Object valueInt) {
		return findByProperty(VALUE_INT, valueInt);
	}

	public List<ArchetypeData> findByValueDouble(Object valueDouble) {
		return findByProperty(VALUE_DOUBLE, valueDouble);
	}

	public List<ArchetypeData> findBySessionId(Object sessionId) {
		return findByProperty(SESSION_ID, sessionId);
	}

	public List<ArchetypeData> findByInstanceIndex(Object instanceIndex) {
		return findByProperty(INSTANCE_INDEX, instanceIndex);
	}

	public List<ArchetypeData> findByTolvenContext(Object tolvenContext) {
		return findByProperty(TOLVEN_CONTEXT, tolvenContext);
	}

	public List<ArchetypeData> findByValueAtPath(Object valueAtPath) {
		return findByProperty(VALUE_AT_PATH, valueAtPath);
	}

	public List<ArchetypeData> findByDeleted(Object deleted) {
		return findByProperty(DELETED, deleted);
	}

	public List findAll() {
		log.debug("finding all ArchetypeData instances");
		try {
			String queryString = "from ArchetypeData";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ArchetypeData merge(ArchetypeData detachedInstance) {
		log.debug("merging ArchetypeData instance");
		try {
			ArchetypeData result = (ArchetypeData) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ArchetypeData instance) {
		log.debug("attaching dirty ArchetypeData instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ArchetypeData instance) {
		log.debug("attaching clean ArchetypeData instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
