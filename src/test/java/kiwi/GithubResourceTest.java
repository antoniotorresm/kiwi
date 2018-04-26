package kiwi;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.databind.ObjectMapper;

import aiss.model.github.InviteCollaboratorResult;
import aiss.model.github.RepositoryCreateResult;
import aiss.model.resource.GithubResource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GithubResourceTest {

	private static GithubResource github;
	private static RepositoryCreateResult result;
	private static InviteCollaboratorResult inviteResult;
	
	private static final String REPO_NAME = "test5";
	private static final String USER = "JMGuisadoG";
	
	@BeforeClass
	public static void init() {
		github = new GithubResource();
	}
		
	@Test
	public void A_RepositoryCreateTest() {
		// Creating a new repository with an unused name
		result = github.createRepository(REPO_NAME);
		assertTrue(result.getName().equals(REPO_NAME));
		
		// Trying to create a repository with a name already used
		result = github.createRepository(REPO_NAME);
		assertNull(result.getName());
		assertNull(result.getHtmlUrl());				
	}
	
	@Test(expected = NullPointerException.class)
	public void B_RepositoryCreateNullTest() { 
		result = github.createRepository(null);
	}
	
	@Test
	public void C_InviteCollaboratorTest() {		
		inviteResult = github.inviteCollaborator(REPO_NAME, USER);
		assertTrue("User invited not matching the one provided in the test. Lol.", inviteResult.getInvitee().getLogin().equals(USER));
		assertTrue("Name of the proposed repo not matching the one provided in the test for collaboration. Lol.", inviteResult.getRepository().getName().equals(REPO_NAME));		
	}

	// TODO: Before class tidying up everything?
}
