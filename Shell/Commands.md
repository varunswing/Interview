## **🚀 Important Git & GitHub Commands**  

Here’s a list of essential Git commands that will help you efficiently manage your repositories in **GitHub**.

---

### **1️⃣ Configuring Git**
📌 Set up your username and email (mandatory before commits).  
```sh
git config --global user.name "Your Name"
git config --global user.email "your-email@example.com"
```
🔹 **Check current Git config**:  
```sh
git config --list
```
🔹 **Fix multiple user.email values error**:  
```sh
git config --global --unset user.email
git config --global --replace-all user.email "your-email@example.com"
```

---

### **2️⃣ Creating & Cloning a Repository**
📌 **Initialize a new Git repository**  
```sh
git init
```
📌 **Clone an existing GitHub repo**  
```sh
git clone <repo-URL>
```
🔹 Example:  
```sh
git clone https://github.com/user/repo.git
```

---

### **3️⃣ Working with Branches**
📌 **Check all branches**  
```sh
git branch
```
📌 **Create a new branch**  
```sh
git branch <branch-name>
```
📌 **Switch to another branch**  
```sh
git checkout <branch-name>
```
📌 **Create and switch to a new branch**  
```sh
git checkout -b <branch-name>
```
📌 **Delete a branch**  
```sh
git branch -d <branch-name>  # Safe delete
git branch -D <branch-name>  # Force delete
```
📌 **Rename the current branch**  
```sh
git branch -m <new-branch-name>
```

---

### **4️⃣ Making Changes & Committing**
📌 **Check file status**  
```sh
git status
```
📌 **Stage changes for commit**  
```sh
git add <file>             # Add a specific file
git add .                  # Add all modified files
```
📌 **Commit changes**  
```sh
git commit -m "Your commit message"
```
📌 **Amend the last commit**  
```sh
git commit --amend -m "New commit message"
```

---

### **5️⃣ Pushing & Pulling Code**
📌 **Push code to GitHub**  
```sh
git push origin <branch-name>
```
📌 **Push and set upstream (for new branches)**  
```sh
git push --set-upstream origin <branch-name>
```
📌 **Pull latest changes from GitHub**  
```sh
git pull origin <branch-name>
```

---

### **6️⃣ Merging & Rebasing**
📌 **Merge another branch into the current branch**  
```sh
git merge <branch-name>
```
📌 **Rebase branch (rewrite history)**  
```sh
git rebase <branch-name>
```
📌 **Abort rebase (if conflicts occur)**  
```sh
git rebase --abort
```

---

### **7️⃣ Undoing Changes**
📌 **Undo changes in a file**  
```sh
git checkout -- <file>
```
📌 **Unstage a file (remove from `git add`)**  
```sh
git reset <file>
```
📌 **Undo last commit (keep changes unstaged)**  
```sh
git reset --soft HEAD~1
```
📌 **Undo last commit (discard changes)**  
```sh
git reset --hard HEAD~1
```

---

### **8️⃣ Deleting Files**
📌 **Remove a file from Git and local directory**  
```sh
git rm <file>
```
📌 **Remove a file from Git but keep it locally**  
```sh
git rm --cached <file>
```

---

### **9️⃣ Stashing Changes**
📌 **Save changes temporarily (stash them)**  
```sh
git stash
```
📌 **List all stashes**  
```sh
git stash list
```
📌 **Apply the last stashed changes**  
```sh
git stash apply
```
📌 **Apply and remove the last stash**  
```sh
git stash pop
```

---

### **🔟 Checking Logs & History**
📌 **View commit history**  
```sh
git log
```
📌 **View a one-line summary of commits**  
```sh
git log --oneline
```
📌 **View the last 5 commits**  
```sh
git log -5
```

---

### **1️⃣1️⃣ Git Cherry-Pick**
📌 **Apply a specific commit from another branch**  
```sh
git cherry-pick <commit-hash>
```

---

### **1️⃣2️⃣ Git Reset vs. Revert**
📌 **Reset to a previous commit (remove commits permanently)**  
```sh
git reset --hard <commit-hash>
```
📌 **Revert a commit (keep history intact)**  
```sh
git revert <commit-hash>
```

---

### **1️⃣3️⃣ Dealing with Remotes**
📌 **Check remote repositories**  
```sh
git remote -v
```
📌 **Add a remote repository**  
```sh
git remote add origin <repo-URL>
```
📌 **Change the remote URL**  
```sh
git remote set-url origin <new-URL>
```
📌 **Remove a remote repository**  
```sh
git remote remove origin
```

---

### **1️⃣4️⃣ Resolving Merge Conflicts**
📌 **After fixing conflicts, mark as resolved**  
```sh
git add <file>
git commit -m "Resolved merge conflict"
```
📌 **Abort merge (if needed)**  
```sh
git merge --abort
```

---

### **1️⃣5️⃣ GitHub-Specific Commands**
📌 **Fork a repo** → Use GitHub UI  
📌 **Create a Pull Request (PR)** → Use GitHub UI  
📌 **View PR details in CLI**  
```sh
gh pr list
gh pr view <PR-number>
```

---

### **1️⃣6️⃣ GitHub SSH Authentication**
📌 **Test SSH connection with GitHub**  
```sh
ssh -T git@github.com
```
📌 **Generate SSH key (if needed)**  
```sh
ssh-keygen -t rsa -b 4096 -C "your-email@example.com"
```
📌 **Add SSH key to SSH agent**  
```sh
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa
```
📌 **Add the public key to GitHub**  
1. Copy the key  
   ```sh
   cat ~/.ssh/id_rsa.pub
   ```
2. Add it to **GitHub → Settings → SSH Keys**  

---

### **🔥 Pro Tips**
✅ Use **`git status`** often to check your changes.  
✅ Use **`git log --oneline --graph`** to see a branch tree.  
✅ Use **`git pull --rebase`** instead of `git pull` to avoid unnecessary merge commits.  
✅ Use **`.gitignore`** to exclude files from Git tracking.  

---
