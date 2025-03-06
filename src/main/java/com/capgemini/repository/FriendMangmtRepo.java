package com.capgemini.repository;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.constants.FrndMgtConstants;
import com.capgemini.exceptions.ResourceNotFoundException;
import com.capgemini.model.CommonFriendsListResp;
import com.capgemini.model.RecievesUpdatesResp;
import com.capgemini.model.FriendsListResp;
import com.capgemini.model.FriendManagementResp;

/**
 * This is a repository class containing methods for friend management api's
 * 
 *
 */

@Repository
public class FriendMangmtRepo {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	FriendManagementResp fmError;

	@Autowired
	JdbcTemplate jdbcTemplate;


	@Autowired
	public FriendMangmtRepo(FriendManagementResp fmError,JdbcTemplate jdbcTemplate){
		this.fmError=fmError;
		this.jdbcTemplate=jdbcTemplate;
	}



	/**
	 * 
	 * This method adds new friend connection request to database
	 * @param userReq
	 * @return FriendManagementValidation object with status message
	 */

	public FriendManagementResp createFriendConnection(com.capgemini.model.CreateFriendship userReq) throws ResourceNotFoundException {

		LOG.info(" FriendMangmtRepo method addNewFriendConnection");
		String requestor = userReq.getRequestor();
		String target = userReq.getTarget();
		String query = "SELECT email FROM user_managmnt";

		List<String> emails = jdbcTemplate.queryForList(query, String.class);
		fmError.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
		fmError.setDescription(FrndMgtConstants.FRND_MGMT_SUCCESSFULLY_ADDED);

		if (requestor.equals(target)) {
			fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
			fmError.setDescription(FrndMgtConstants.FRND_MGMT_EMAIL_SAME);
			return fmError;
		}

		if (emails.contains(requestor) && emails.contains(target)) {

			if (!isBlocked(requestor, target)) {
				if (isAlreadyFriend(requestor, target)) {
					fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
					fmError.setDescription(FrndMgtConstants.FRND_MGMT_ALREADY_FRIEND);
				} else {
					connectFriend(requestor, target);
					connectFriend(target, requestor);
				}
			} else {
				fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				fmError.setDescription(FrndMgtConstants.FRND_MGMT_TARGET_BLOCKED);
			}


		}else if(!emails.contains(requestor) && !emails.contains(target)) {
			insertEmail(requestor);   
			insertEmail(target);
			connectFriend(requestor, target);
			connectFriend(target, requestor);
		} else if (emails.contains(requestor)) {
			insertEmail(target);
			connectFriend(requestor, target);
			connectFriend(target, requestor);
		} else {
			insertEmail(requestor);
			connectFriend(requestor, target);
			connectFriend(target, requestor);
		}
		
		return fmError;

	} 

	/**
	 * This method is used to insert friends
	 * 
	 * @param firstEmail
	 * @param secondEmail
	 */
	private void connectFriend(String firstEmail, String secondEmail) {
		LOG.info(" FriendMangmtRepo method connectFriend");
		String requestorId = getId(firstEmail);
		String friendList = getFriendList(secondEmail);

		friendList = friendList.isEmpty() ? requestorId : friendList + "," + requestorId;

		jdbcTemplate.update("update user_managmnt " + " set friend_list = ?" + " where email = ?",
				new Object[] { friendList, secondEmail });
	}

	private int insertEmail(String email) {
		LOG.info(" FriendMangmtRepo method insertEmail");
		return jdbcTemplate.update("insert into user_managmnt(email, friend_list, subscriber, subscribedBy, updated, updated_timestamp) values(?,?,?,?,?,?)",
				new Object[] { email,"" , "", "", "",
						new Timestamp((new Date()).getTime()) });
	} 


	/**
	 * This method is invoked to check whether the friend is already connected
	 * 
	 * @param requestor
	 * @param target
	 * @return boolean
	 */
	private boolean isAlreadyFriend(String requestor, String target) throws ResourceNotFoundException {
		LOG.info(" FriendMangmtRepo method isAlreadyFriend");
		boolean alreadyFriend = false;

		String requestorId = getId(requestor);
		String targetId = getId(target);

		String requestorFriendList = getFriendList(requestor);
		String[] requestorFriends = requestorFriendList.split(",");

		String targetFirendList = getFriendList(target);
		String[] targetFriends = targetFirendList.split(",");

		if (Arrays.asList(requestorFriends).contains(targetId) && Arrays.asList(targetFriends).contains(requestorId)) {
			alreadyFriend = true;
		}
		return alreadyFriend;

	}

	/**
	 * this method is invoked to get Id of particular email
	 * 
	 * @param email
	 * @return String
	 */
	private String getId(String email) {
		String sql = "SELECT id FROM user_managmnt WHERE email=?";
		String requestorId = (String) jdbcTemplate.queryForObject(sql, new Object[] { email }, String.class);
		return requestorId;
	}

	/**
	 * This method is invoked to get the list of friends
	 * 
	 * @param email
	 * @return String
	 */
	private String getFriendList(String email) {
		String sqlrFriendList = "SELECT friend_list FROM user_managmnt WHERE email=?";
		String friendList = (String) jdbcTemplate.queryForObject(sqlrFriendList, new Object[] { email }, String.class);


		return friendList;

	}

	/**
	 * This method is invoked to check whether the target is blocked or not
	 * 
	 * @param requestor_email
	 * @param target_email
	 * @return boolean
	 */
	private boolean isBlocked(String requestor_email, String target_email) throws ResourceNotFoundException {
		boolean status = false;
		try{
			String sqlrFriendList = "SELECT Subscription_Status FROM unsubscribe WHERE Requestor_email=? AND Target_email=?";
			String Subscription_Status = (String) jdbcTemplate.queryForObject(sqlrFriendList,
					new Object[] { requestor_email, target_email }, String.class);
			System.out.println("---Subscription_Status ---204----"  +Subscription_Status);

			if (Subscription_Status.equalsIgnoreCase("Blocked")) {
				status = true;
			}
		} catch (Exception e) {
			LOG.debug(e.getLocalizedMessage());

		}

		return status;
	}




	/**
	 *  This method get the list of friends
	 * @param friendListRequest
	 * @return UserFriendsListResponse
	 * @throws ResourceNotFoundException
	 */

	public FriendsListResp getFriendsList(com.capgemini.model.FriendList friendListRequest) throws ResourceNotFoundException {
		LOG.info(" FriendMangmtRepo method getFriendsList");
		FriendsListResp emailListresponse = new FriendsListResp();
		try{
			String query = "SELECT email FROM user_managmnt";

			List<String> emails = jdbcTemplate.queryForList(query, String.class);
			if(emails!=null && emails.contains(friendListRequest.getEmail())) {
				LOG.info(":: Friend List " + friendListRequest.getEmail());
			
			
			String friendList = getFriendList(friendListRequest.getEmail());
			if ("".equals(friendList) || friendList==null) {
				emailListresponse.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				emailListresponse.setCount(0);
			} else {
				String[] friendListQueryParam = friendList.split(",");
				List<String> friends = getEmailByIds(Arrays.asList(friendListQueryParam));
				if(friends.size()==0) {

				}else {
					emailListresponse.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
					emailListresponse.setCount(friends.size());
					for (String friend : friends) {
						emailListresponse.getFriends().add(friend);
					}
				}
			}
			}else{
				emailListresponse.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				
			}
		}catch(Exception e){

			throw new ResourceNotFoundException();
		}
		return emailListresponse;

	} 



	/**
	 *  Gets email ids
	 * @param friendListQueryParam
	 * @return List
	 */


	private List<String> getEmailByIds(List<String> friendListQueryParam){

		StringJoiner email_Ids = new StringJoiner(",", "SELECT email FROM user_managmnt WHERE id in (", ")");

		for (String friendId : friendListQueryParam) {
			email_Ids.add(friendId);
		}
		String query = email_Ids.toString();
		return (List<String>) jdbcTemplate.queryForList(query, new Object[] {}, String.class);
	} 



	/**
	 * This API is invoked to get the common friends between two friends
	 * @param email1
	 * @param email2
	 * @return CommonFriendsListResponse
	 */
	public CommonFriendsListResp retrieveCommonFriendList(String email1 ,String email2)throws ResourceNotFoundException {
		LOG.info(" FriendMangmtRepo method retrieveCommonFriendList");
		CommonFriendsListResp commonFrndListresponse = new CommonFriendsListResp();
		try{

			String friendList1 = getFriendList(email1);
			String friendList2 = getFriendList(email2);
			String[] friendList1Container = friendList1.split(",");
			String[] friendList2Container = friendList2.split(",");

			Set<String> friend1Set = new HashSet<String>(Arrays.asList(friendList1Container));
			Set<String> friend2Set = new HashSet<String>(Arrays.asList(friendList2Container));
			friend1Set.retainAll(friend2Set);

			List<String> friends = getEmailByIds(new ArrayList<String>(friend1Set));
			if(friends.size() == 0) {

			}else {
				commonFrndListresponse.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
				commonFrndListresponse.setCount(friends.size());
				for (String friend : friends) {
					commonFrndListresponse.getFriends().add(friend);
				}}
		}catch(Exception e){
			throw new ResourceNotFoundException();
		}
		return commonFrndListresponse;
	} 





	/**
	 * This API is invoked to unsubscribe and remove id from subscribe and subscribedBy column
	 * @param subscriber
	 * @return FriendManagementValidation
	 * @throws ResourceNotFoundException
	 */
	public FriendManagementResp unSubscribeTargetFriend(com.capgemini.model.Subscriber subscriber)throws ResourceNotFoundException {
		LOG.info(" FriendMangmtRepo method retrieveCommonFriendList");
		try{
			String requestor = subscriber.getRequestor();
			String target = subscriber.getTarget();

			String query = "SELECT email FROM user_managmnt";
			List<String> emails = jdbcTemplate.queryForList(query, String.class);

			if(emails.contains(requestor) && emails.contains(target)) {
				String sql = "SELECT subscriber FROM user_managmnt WHERE email=?";
				String subscribers = (String) jdbcTemplate.queryForObject(
						sql, new Object[] { requestor }, String.class);
				if(subscribers == null || subscribers.isEmpty()) {
					fmError.setStatus("Failed");
					fmError.setDescription("Requestor does not subscribe to any email");
				}else {

					String[] subs = subscribers.split(",");
					ArrayList<String> subscriberList = new ArrayList<>(Arrays.asList(subs));
					String targetId = getId(target);
					if(subscriberList.contains(targetId)) {
						StringJoiner sjTarget = new StringJoiner(",");
						for(String sub:subscriberList) {
							if(!sub.equals(targetId)) {
								sjTarget.add(sub);
							}
						}
						updateQueryForSubscriber(sjTarget.toString(),requestor);
						String sqlQuery = "SELECT subscribedBy FROM user_managmnt WHERE email=?";
						String subscribedBys = (String) jdbcTemplate.queryForObject(
								sqlQuery, new Object[] { target }, String.class);
						String[] subscribedBy = subscribedBys.split(",");
						ArrayList<String> subscribedByList = new ArrayList<>(Arrays.asList(subscribedBy));
						String requestorId = getId(requestor);
						if(subscribedByList.contains(requestorId)) {
							StringJoiner sjRequestor = new StringJoiner(",");
							for(String sub:subscribedByList) {
								if(!sub.equals(requestorId)) {
									sjRequestor.add(sub);
								}
							}
							updateQueryForSubscribedBy(sjRequestor.toString(),target);
						}

						updateUnsubscribeTable(requestor, target,FrndMgtConstants.FRND_MGMT_BLOCKED);
						fmError.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
						fmError.setDescription(FrndMgtConstants.FRND_MGMT_SUCCESSFULLY_UNSUBSCRIBED);
					}else {
						fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
						fmError.setDescription(FrndMgtConstants.FRND_MGMT_NO_TARGET);
					}
				}
			}else{
				fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				fmError.setDescription(FrndMgtConstants.FRND_MGMT_INVALID_EMAIL);
			}
		}catch(Exception e){
			throw new ResourceNotFoundException();
		}
		return fmError;
	}



	/**
	 * 
	 * @param targetId
	 * @param requestor
	 * @return boolean
	 */


	private void updateQueryForSubscriber(String targetId, String requestor) {
		jdbcTemplate.update(
				"update user_managmnt " + " set subscriber = ? " + " where email = ?",
				new Object[] { targetId, requestor });
	}




	/**
	 * 
	 * @param requestorId
	 * @param target
	 */


	private void updateQueryForSubscribedBy(String requestorId, String target) {
		jdbcTemplate.update(
				"update user_managmnt " + " set subscribedBy = ? " + " where email = ?",
				new Object[] { requestorId, target });
	}


	/**
	 * 
	 * @param recieverId
	 * @param sender
	 */

	private void updateQueryForUpdated(String recieverId, String sender) {
		jdbcTemplate.update(
				"update user_managmnt " + " set updated = ? " + " where email = ?",
				new Object[] { recieverId, sender });
	}


	/**
	 * 
	 * @param requestor
	 * @param target
	 * @param status
	 */

	private void updateUnsubscribeTable(String requestor, String target,String status) {
		jdbcTemplate.update("insert into unsubscribe(Requestor_email, Target_email, Subscription_Status) values(?, ?, ?)",
				new Object[] { requestor, target, status });
	}


	/**
	 * This API is invoked to subscribe to updates from an email address
	 * @param subscriber
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public FriendManagementResp subscribeTargetFriend(com.capgemini.model.Subscriber subscriber)
			throws ResourceNotFoundException {
		try{
			String requestor = subscriber.getRequestor();
			String target = subscriber.getTarget();

			String query = "SELECT email FROM user_managmnt";
			List<String> emails = jdbcTemplate.queryForList(query, String.class);

			if(requestor.equals(target)) {
				fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				fmError.setDescription("Requestor and target should not be same");
				return fmError;
			}
			fmError.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
			fmError.setDescription(FrndMgtConstants.FRND_MGMT_SUCCESSFULLY_SUBSCRIBED);
			boolean isBlocked = isBlocked(requestor, target);
			if (!isBlocked) {
				if (emails.contains(target) && emails.contains(requestor)) {
					String subscribers = getSubscriptionList(requestor);
					String targetId = getId(target);
					if (subscribers == null || subscribers.isEmpty()) {
						updateQueryForSubscriber(targetId, requestor);

						updateSubscribedBy(requestor, target);

					} else {
						String[] subs = subscribers.split(",");
						ArrayList<String> al = new ArrayList<String>(Arrays.asList(subs));

						if (!al.contains(targetId)) {
							targetId = subscribers + "," + targetId;
							updateQueryForSubscriber(targetId, requestor);

							updateSubscribedBy(requestor, target);

						} else {
							fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
							fmError.setDescription(FrndMgtConstants.FRND_MGMT_TARGET_SUBSCRIBED);
						}
					}

				} else {
					fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
					fmError.setDescription(FrndMgtConstants.FRND_MGMT_CHECK_EMAIL);
				}
			}else {
				fmError.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				fmError.setDescription(FrndMgtConstants.FRND_MGMT_TARGET_BLOCKED);
			}
		}catch(Exception e){
			
			throw new ResourceNotFoundException();
		}
		return fmError;
	}






	/**
	 * This method is used to get all the subscriber for an email
	 * @param email
	 * @return
	 */
	private String getSubscriptionList(String email) {
		String sqlrFriendList = "SELECT subscriber FROM user_managmnt WHERE email=?";
		String friendList = (String) jdbcTemplate.queryForObject(sqlrFriendList, new Object[] { email }, String.class);
		return friendList;
	}



	/**
	 * This method is used to update the subscribedBy column
	 * @param requestor
	 * @param target
	 */
	private void updateSubscribedBy(String requestor, String target) {
		String requestorId = getId(requestor);
		String subscribedList = getSubscribedByList(target);
		if (subscribedList.isEmpty()) {
			updateQueryForSubscribedBy(requestorId, target);
		}else {
			String[] subscr = subscribedList.split(",");
			ArrayList<String> subscrList = new ArrayList<String>(Arrays.asList(subscr));

			if (!subscrList.contains(requestorId)) {
				requestorId = subscribedList + "," + requestorId;
				updateQueryForSubscribedBy(requestorId, target);
			}
		}
	}


	/**
	 * This method is used to get the subscribedBy Ids for a particular email
	 * @param email
	 * @return
	 */
	private String getSubscribedByList(String email) {
		String sqlrFriendList = "SELECT subscribedBy FROM user_managmnt WHERE email=?";
		String friendList = (String) jdbcTemplate.queryForObject(sqlrFriendList, new Object[] { email }, String.class);
		return friendList;
	}








	/**
	 * This API is invoked to retrive all email address that can receive updates from an email address
	 * @param emailsList
	 * @return EmailsListRecievesUpdatesResponse
	 */

	public RecievesUpdatesResp emailListRecievesupdates(
			com.capgemini.model.RecievesUpdates emailsList) throws ResourceNotFoundException {

		RecievesUpdatesResp EmailsList = new RecievesUpdatesResp();
		try{
			String query = "SELECT email FROM user_managmnt";
			List<String> emails = jdbcTemplate.queryForList(query, String.class);
			String sender = emailsList.getSender();
			String text = emailsList.getText();
			text = text.trim();
			String reciever = text.substring(text.lastIndexOf(' ') + 1).substring(1);

			if (emails.contains(sender)) {
				if (emails.contains(reciever)) {
					String recieverId = getId(reciever);
					updateQueryForUpdated(recieverId, sender);
				} else {
					insertEmail(reciever);
					String recieverId = getId(reciever);
					updateQueryForUpdated(recieverId, sender);
				}

				String friendList = getFriendList(sender);
				String[] senderFriends = friendList.split(",");

				String subscribedBy = getSubscribedByList(sender);
				String[] subscribedFriends = subscribedBy.split(",");

				Set<String> set = new HashSet<String>();
				
				if(senderFriends[0].equals("") && subscribedFriends[0].equals("")) {

				}else if(senderFriends[0].equals("")) {
					set.addAll(Arrays.asList(subscribedFriends));
				}else if(subscribedFriends[0].equals("")){
					set.addAll(Arrays.asList(senderFriends));
				}else {
					set.addAll(Arrays.asList(senderFriends));
					set.addAll(Arrays.asList(subscribedFriends));
				}
				List<String> emailsUnion = new ArrayList<String>(set);

				List<String> commonEmails = getEmailByIds(emailsUnion);

				if (!commonEmails.contains(reciever)) {
					commonEmails.add(reciever);
				}

				EmailsList.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
				for (String email : commonEmails) {
					EmailsList.getFriends().add(email);
				}
			} else {
				EmailsList.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
			}
		}catch(Exception e){
			
			throw new ResourceNotFoundException();
		}
		return EmailsList;
	}



}
