### General Items
<pre>
# Update Stale Repo credentials (on Windows)
# if you have your credentials stored in your git shell and you have recently changed
# your password, this command will prompt you to supply your new credentials
git config --global credential.helper wincred
  
# Pretty Logs
# This command will generate a more eye pleasing format of the commit log
git log --graph --pretty=format:"%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset" --abbrev-commit
  
# Add files
git add <file or folder name>
  
# Commit.
# Always precede your commit with the related Jira ticket (i.e. ES-1234)
git commit -m "<Jira_ticket_number>: Add your comment here"
  
# This command will help error related to directory path too long
git config core.longpaths true
  
# To create local Git repo folder
git init <repo_folder_name>
 
# Git command to clone branch
git clone -b <branch_name> <repo_url>
  
# Cherry Pick a commit onto your branch. Make sure you are on the branch that will receive the commit
git cherry-pick <commit_hash>
</pre>
  
### Patching
<pre>
# There are several ways to create a patch. Replace <your_patch_file.patch> with the file name and/or path to the file name if it does not reside in the same folder that you are executing the commands.
  
# Create a patch from a commit
git format-patch <commit_hash> --stdout > <your_patch_file.patch>
  
# Create a patch from an un-committed change (i.e. you still need to 'git add' or before you have 'git commit' ed a change)
git diff > <your_patch_file.patch>
  
# Check to see what changes are included in the patch
git apply --stat <your_patch_file.patch>
  
# Check to see what conflicts you might run into applying the patch, without actually applying the patch
git apply --check <your_patch_file.patch>
  
# Apply the patch
git apply <your_patch_file.patch>
</pre>

### Branch Management
<pre>
# Create a new branch (myfeature) from develop
git checkout -b myfeature develop
  
# Push a new local branch (<branch>) to origin
git push -u origin <branch>
 
# Force a push to origin
git push origin <branch> --force
  
# Create feature branch (<feature_branch>)
git checkout -b <feature_branch> develop
  
# Merge from a branch using their changes (i.e. discarding ours) at a specific commit on their branch
# This could be used to update the "release" branch from the "develop" branch at a specific commit
git merge -X theirs <branch_name> <commit_hash>
  
# Delete a remote branch (exercise with caution)
git push origin --delete <branch_name>
  
# Delete a local branch (* use -D to force the delete if you have unmerged changes)
git branch -d <branch_name>
  
# Compare "branchName" w/ the current master branch
git diff --name-status master..<branch_name>
  
# List out the current branches that you have local and view the HEAD commit on each
git branch -v
  
# List the 10 most recent (local) branches you have worked on (change count to see more or less)
git for-each-ref --count=10 --sort=-committerdate refs/heads/ --format="%(refname:short)"
  
# List the set of commits that have occurred from a particular commit (i.e. a TAG) up until HEAD
# This can help us gather a simple list of what has been changed
git log --oneline --no-merges <taggname_or_commit_hash>..HEAD
 
# Find the common ancestor (commit) between two branches
git merge-base <branch-A> <branch-B>
</pre>

### Tag Management
<pre>
# Create a tag with comments
git tag -a <release_number> -m "<your_comments>"
  
# Push the tag to origin
git push --follow-tags
  
# Delete a tag
git tag -d <release_number>
  
# Remove the tag from origin
git push origin :refs/tags/<release_number>
</pre>

### Git Aliases
Git aliases provide short cuts to otherwise lengthy commands, saving you some key strokes for useful functionality. Aliases should be placed in your local, global Git configuration under the [alias] section. Git config can be found under your user home directory (i.e. c:\users\<your_username>\.gitconfig)
<pre>
[alias]
  prune = fetch --prune
  # Removes local branches that don't exist on the remote origin
  # https://git-scm.com/docs/git-fetch#git-fetch--p
 
  undo = reset --soft HEAD^
  # Will reset to head but still show you changes as uncommitted... not as permanent as --hard
  # https://git-scm.com/docs/git-reset#git-reset-emgitresetemltmodegtltcommitgt
 
  stash-all = stash save --include-untracked
  # Stash all commits and un tracked files (if you need to jump to a new branch will in progress with another change on another branch)
  # https://git-scm.com/docs/git-stash
 
  glog = log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset'
  # Displays a colorful, concise version of the git log for the current branch
</pre>

### Diff Management
The following is a set of configuration changes that you can make to your local, global, Git configuration to enhance the diff process when using the command line for Git operations. 
The following assumes that you have downloaded and installed BeyondCompare version 3. 
<pre>
# Apply the following settings to your .gitconf file, found in your home, user directory (i.e. c:\users\<your_username>\.gitconfig
# Change the location of the install path to your diff tool listed below.
[diff]
tool = bc3
 
[difftool]
prompt = false
 
[difftool "bc3"]
cmd = \"C:/opt/BeyondCompare3/bcomp.exe\" "$LOCAL" "$REMOTE"
  
# Suppress whitespace changes when using git diff
git diff -w
</pre>

### Other
Random/Miscellaneous commands
<pre>
# Get a list of commits by person
git shortlog
  
# Get a count of commits by person
git shortlog -sn
  
# Discover who changed a line or set of lines of code
# i.e. git blame -L5,10 _components.buttons.scss
git blame -L<starting_lin_num>,<ending_line_num> <filepath>
  
# View a simplified list of the latest commits across all branches
git log --all --oneline --no-merges
  
# View a simplified list, in a particular time frame, of the latest commits across all branches
git log --all --since='2 weeks' --oneline --no-merges
  
# View a simplified list of just your commits
git log --all --oneline --no-merges --author=<your_email_address>
  
# View all commits made today
git log --since=00:00:00 --all --no-merges --oneline
</pre>