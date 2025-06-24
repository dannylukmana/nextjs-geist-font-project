# Setting up the GitHub Repository

Follow these steps to host this project on GitHub:

1. Create a new repository on GitHub:
   - Go to https://github.com/new
   - Name: `virtual-tour-android`
   - Description: "Android app for viewing nearby virtual tours using GPS"
   - Make it Public
   - Don't initialize with README (we already have one)
   - Click "Create repository"

2. Push the code to GitHub (run these commands in terminal):
```bash
# If you haven't configured Git yet:
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Add the remote repository
git remote add origin https://github.com/YOUR_USERNAME/virtual-tour-android.git

# Push the code
git branch -M main
git push -u origin main
```

3. Verify the Repository:
   - Go to https://github.com/YOUR_USERNAME/virtual-tour-android
   - You should see all the project files
   - The README.md will be displayed on the main page

4. To Download:
   - Click the green "Code" button
   - Choose "Download ZIP" or use git clone
   - Follow the setup instructions in README.md

Note: Replace `YOUR_USERNAME` with your actual GitHub username in the commands above.
