/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package game.web.wicket.pages;

import game.db.GamePageFacade;
import game.web.wicket.GameSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;



public final class RegistrationPage extends WebPage
{
	/**
	 * Construct
	 */
	private String login;
	private String password;
	private String repeatPassword;
	private String feedback;
	private GamePageFacade gamePageFacade;

	public RegistrationPage()
	{
		this(null);
	}


	public RegistrationPage(final PageParameters parameters)
	{
		gamePageFacade = ((GameSession)getSession()).getGamePageFacade();

		FeedbackPanel feedbackLabel = new FeedbackPanel("feedback");//, new PropertyModel<>(this, "feedback"));
		feedbackLabel.setOutputMarkupId(true);
		PasswordTextField passwordTextField1 = new PasswordTextField("password", new PropertyModel(this, "password"));
		PasswordTextField passwordTextField2 = new PasswordTextField("repeat_password", new PropertyModel(this, "repeatPassword"));

		Form registrationForm = new Form<>("registration_form");
		TextField loginField =new TextField<String>("login", new PropertyModel(this, "login"));
		loginField.setRequired(true);
		loginField.add(new UsernameValidator());
		registrationForm.add(loginField);
		registrationForm.add(new EqualPasswordInputValidator(passwordTextField1, passwordTextField2));
		registrationForm.add(passwordTextField1);
		registrationForm.add(passwordTextField2);
		registrationForm.add(new AjaxButton("submit_button") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				gamePageFacade.addPlayer(login, password);
				setResponsePage(MySignInPage.class);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				super.onError(target, form);
				target.add(feedbackLabel);
			}
		});
		add(feedbackLabel);
		add(registrationForm);

	}

	public class UsernameValidator implements IValidator<String> {

		public void validate(IValidatable<String> validatable) {
			String chosenUserName = validatable.getValue();

			if(gamePageFacade.getPlayer(chosenUserName) != null){
				ValidationError error = new ValidationError(this);
				error.setMessage("Unfortunately such user is already exists, choose another login");
				validatable.error(error);
			}
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
