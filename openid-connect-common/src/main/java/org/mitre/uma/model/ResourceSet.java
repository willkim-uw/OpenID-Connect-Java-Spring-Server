/*******************************************************************************
 * Copyright 2015 The MITRE Corporation
 *   and the MIT Kerberos and Internet Trust Consortium
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
 *******************************************************************************/
package org.mitre.uma.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "resource_set")
@NamedQueries ({
		@NamedQuery(name = ResourceSet.QUERY_BY_OWNER, query = "select r from ResourceSet r where r.owner = :" + ResourceSet.PARAM_OWNER),
		@NamedQuery(name = ResourceSet.QUERY_BY_OWNER_AND_CLIENT, query = "select r from ResourceSet r where r.owner = :" + ResourceSet.PARAM_OWNER + " and r.clientId = :" + ResourceSet.PARAM_CLIENTID)
})
public class ResourceSet {

	public static final String QUERY_BY_OWNER = "ResourceSet.queryByOwner";
	public static final String QUERY_BY_OWNER_AND_CLIENT = "ResourceSet.queryByOwnerAndClient";

	public static final String PARAM_OWNER = "owner";
	public static final String PARAM_CLIENTID = "clientId";

	private Long id;
	private String name;
	private String uri;
	private String type;
	private Set<String> scopes;
	private String iconUri;
	
	private String owner; // username of the person responsible for the registration (either directly or via OAuth token)
	private String clientId; // client id of the protected resource that registered this resource set via OAuth token
	
	private Collection<Claim> claimsRequired;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the uri
	 */
	@Basic
	@Column(name = "uri")
	public String getUri() {
		return uri;
	}
	
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	/**
	 * @return the type
	 */
	@Basic
	@Column(name = "rs_type")
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the scopes
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "scope")
	@CollectionTable(
		name = "resource_set_scope",
		joinColumns = @JoinColumn(name = "owner_id")
	)
	public Set<String> getScopes() {
		return scopes;
	}
	
	/**
	 * @param scopes the scopes to set
	 */
	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
	}
	
	/**
	 * @return the iconUri
	 */
	@Basic
	@Column(name = "icon_uri")
	public String getIconUri() {
		return iconUri;
	}
	
	/**
	 * @param iconUri the iconUri to set
	 */
	public void setIconUri(String iconUri) {
		this.iconUri = iconUri;
	}
	
	/**
	 * @return the owner
	 */
	@Basic
	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}
	
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the clientId
	 */
	@Basic
	@Column(name = "client_id")
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the claimsRequired
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "resource_set_id")
	public Collection<Claim> getClaimsRequired() {
		return claimsRequired;
	}

	/**
	 * @param claimsRequired the claimsRequired to set
	 */
	public void setClaimsRequired(Collection<Claim> claimsRequired) {
		this.claimsRequired = claimsRequired;
	}
	
	
	
	
	
}
